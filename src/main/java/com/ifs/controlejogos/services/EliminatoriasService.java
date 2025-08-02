package com.ifs.controlejogos.services;

;
import com.ifs.controlejogos.dto.JogoDTO;
import com.ifs.controlejogos.entities.*;
import com.ifs.controlejogos.form.ConfigJogoFORM;
import com.ifs.controlejogos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
public class EliminatoriasService {
    @Autowired
    EsporteRepository esporteRepository;
    @Autowired
    GrupoRepository grupoRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    JogoRepository jogoRepository;
    @Autowired
    JogoService jogoService;

    public List<Equipe> pegarClassificados(Long esporteId) {
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        //Verificando se todos os jogos foram finalizados
        if (!jogoService.todosJogosFinalizadosPorEsporte(esporteId)) {
            throw new RuntimeException("Ainda há jogos não finalizados neste esporte");
        }

        List<Grupo> grupos = grupoRepository.findByEsporte(esporte);

        List<Equipe> primeirosColocados = new ArrayList<>();
        List<Equipe> segundosColocados = new ArrayList<>();

        //Pegando as equipes do grupo e ordenando (.sorted) por pontuação decrescente (-e.getPontuação())
        for (Grupo grupo : grupos) {
            List<Equipe> ordenadas = grupo.getEquipes().stream()
                    .sorted(Comparator.comparingInt(e -> -e.getPontuacao()))
                    .toList();

            primeirosColocados.add(ordenadas.get(0));
            segundosColocados.add(ordenadas.get(1));
        }

        // Agora alternando o 1º do grupo 1 vs 2º de outro grupo, e assim por diante
        List<Equipe> classificadosOrganizados = new ArrayList<>();
        int tamanho = primeirosColocados.size();
        // evitando confronto com o mesmo grupo
        for (int i = 0; i < tamanho; i++) {
            classificadosOrganizados.add(primeirosColocados.get(i));
            classificadosOrganizados.add(segundosColocados.get((i + 1) % tamanho));
        }
        return classificadosOrganizados;
    }


    public List<Jogo> gerarConfrontos(List<Equipe> equipes, EnumFase fase, ConfigJogoFORM config) {
        List<Jogo> jogos = new ArrayList<>();
        Queue<Equipe> fila = new LinkedList<>(equipes);

        LocalTime horaAtual = config.getHoraInicio();
        Duration duracao = Duration.ofMinutes(config.getDuracaoMinutos());

        Esporte esporte = null;

        while (fila.size() >= 2) {
            Equipe a = fila.poll();
            Equipe b = fila.poll();

            LocalTime horaFim = horaAtual.plus(duracao);

            if (jogoService.haConflitoHorario(config.getData(), horaAtual, horaFim)) {
                throw new RuntimeException("Não é possivel gerar jogos: Há conflitos de horario com jogos ja cadastrados em " + config.getData());
            }

            Jogo jogo = new Jogo();
            jogo.setEquipeA(a);
            jogo.setEquipeB(b);
            jogo.setFase(fase);
            jogo.setData(config.getData());
            jogo.setHoraInicio(horaAtual);
            jogo.setHoraFim(horaAtual.plusMinutes(config.getDuracaoMinutos()));
            jogo.setEsporte(a.getEsporte());

            //if necessario pro esporte não ser atribuido novamente a cada loop, so no primeiro
            if (esporte == null) {
                esporte = a.getEsporte();
            }
            jogos.add(jogo);
            //atualiza o outro lado (esporte)
            esporte.getJogos().add(jogo);

            horaAtual = horaAtual.plus(duracao);

        }
        if (esporte != null) {
            esporteRepository.save(esporte);
        }
        return jogoRepository.saveAll(jogos);
    }

    //Responsável por iniciar a fase eliminatória (pega os classificados, calcula os byes, define a fase inicial)
    public List<JogoDTO> gerarFaseEliminatoria(Long esporteId, ConfigJogoFORM config) {
         esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        List<Equipe> classificados = pegarClassificados(esporteId);

        int totalEquipes = classificados.size();

        //Retornando a proxima potencia igual ou abaixo do numero de equipes;
        int proximaPotencia2 = Integer.highestOneBit(totalEquipes);
        if (proximaPotencia2 < totalEquipes) proximaPotencia2 *= 2;

        int byes = proximaPotencia2 - totalEquipes;

        EnumFase faseInicial;

        if (totalEquipes == 2) {
            faseInicial = EnumFase.FINAL;
        } else if (totalEquipes <= 4) {
            faseInicial = EnumFase.SEMIFINAL;
        } else if (totalEquipes <= 8) {
            faseInicial = EnumFase.QUARTAS;
        } else if (totalEquipes <= 16) {
            faseInicial = EnumFase.OITAVAS;
        } else {
            throw new RuntimeException("Número de classificados não suportado.");
        }

        //Faz uma fila com os classificados e atribui os byes nos primeiros
        Queue<Equipe> filaEquipes = new LinkedList<>(classificados);
        List<Equipe> avancam = new ArrayList<>();
        for (int i = 0; i < byes; i++) {
            Equipe equipe = filaEquipes.poll();
            equipe.setRecebeuBye(true);
            avancam.add(equipe);
            System.out.println("A equipe " + equipe.getNome() + " recebeu bye e passou direto para proxima fase");

        }
        equipeRepository.saveAll(avancam);

        //Gerando confrontos das equipes restantes
        List<Equipe> restantes = new ArrayList<>(filaEquipes);
        List<Jogo> jogos = gerarConfrontos(restantes, faseInicial, config);

        return jogos.stream()
                .map(jogo -> new JogoDTO(jogo))
                .toList();
    }

    public List<JogoDTO> gerarProximaFase(Long esporteId, EnumFase faseAtual, ConfigJogoFORM config) {
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        //Verificando se ja foi gerado jogos com essa fase
        if(jogoRepository.existsByFase(faseAtual.proxima())){
            throw new RuntimeException("Já existe jogos gerados para a fase " + faseAtual );
        }

        //Verificando se todos os jogos daquele esporte e daquela fase foram finalizados
        if (!jogoService.todosJogosFinalizadosPorEsporteEFase(esporteId, faseAtual)) {
            throw new RuntimeException("Todos os jogos da fase " + faseAtual + " devem estar finalizados.");
        }

        List<Jogo> jogosAnteriores = jogoRepository.findByEsporteAndFase(esporte, faseAtual);
        // Coletando os vencedores
        List<Equipe> vencedores = jogosAnteriores.stream()
                .map(Jogo::getVencedorEquipe)
                .toList();

        // Coletando equipes que tiveram bye
        List<Equipe> byes = equipeRepository.findByEsporteAndRecebeuByeTrue(esporte);
        byes.forEach(equipe -> equipe.setRecebeuBye(false));
        equipeRepository.saveAll(byes);

        List<Equipe> todasEquipes = new ArrayList<>();
        todasEquipes.addAll(vencedores);
        todasEquipes.addAll(byes);

        if (faseAtual == EnumFase.FINAL && todasEquipes.size() < 2) {
            String campeao = vencedores.get(0).getNome();
            throw new RuntimeException("O campeonato está finalizado. " + campeao + " é o vencedor");
        }

        //Calculando a proxima fase
        EnumFase proximaFase = faseAtual.proxima();

        List<Jogo> jogos = gerarConfrontos(todasEquipes, proximaFase, config);
        return jogos.stream()
                .map(jogo -> new JogoDTO(jogo))
                .toList();
    }
}
