package com.ifs.controlejogos.services;


import com.ifs.controlejogos.dto.JogoDTO;
import com.ifs.controlejogos.entities.*;
import com.ifs.controlejogos.form.ConfigJogoFORM;
import com.ifs.controlejogos.form.FinalizarJogoFORM;
import com.ifs.controlejogos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private EsporteRepository esporteRepository;
    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private  EquipeRepository equipeRepository;
    //C
    public List<JogoDTO> gerarJogos(Long grupoId, ConfigJogoFORM config) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        Esporte esporte = esporteRepository.findById(grupo.getEsporte().getId())
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));


        List<Equipe> equipes = grupo.getEquipes();
        if (equipes.size() < 2) {
            throw new RuntimeException("É necessário pelo menos 2 equipes para gerar jogos.");
        }

        LocalDate data = config.getData();
        LocalTime horaAtual = config.getHoraInicio();
        Duration duracao = Duration.ofMinutes(config.getDuracaoMinutos());

        List<Jogo> jogos = new ArrayList<>();

        // Gerando jogos entre todas as combinações únicas de equipes

        for (int i = 0; i < equipes.size() - 1; i++) {
            for (int j = i + 1; j < equipes.size(); j++) {

                Jogo jogo = new Jogo();
                jogo.setEsporte(esporte);
                jogo.setEquipeA(equipes.get(i));
                jogo.setEquipeB(equipes.get(j));

                jogo.setData(data);
                jogo.setHoraInicio(horaAtual);
                jogo.setHoraFim(horaAtual.plus(duracao));

                if (haConflitoHorario(config.getData(), horaAtual, horaAtual.plusMinutes(config.getDuracaoMinutos()))) {
                    throw new RuntimeException("Conflito de horário com outro jogo já existente para o dia " + config.getData());
                }

                jogo.setFase(EnumFase.CLASSIFICATORIA);

                jogos.add(jogo);

                //adicionando os jogos em esporte (outro lado da relação)
                esporte.getJogos().add(jogo);

                // Incrementando o horário para o próximo jogo
                horaAtual = horaAtual.plus(duracao);

            }
        }
        esporteRepository.save(esporte);
        jogoRepository.saveAll(jogos);


        return jogos.stream()
                .map(j -> new JogoDTO(j))
                .toList();
    }

    //R
    @Transactional(readOnly = true)
    public List<JogoDTO> listarJogosPorGrupo(Long grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        List<Jogo> jogos = jogoRepository.findByGrupo(grupo);

        return jogos.stream()
                .map(jogodto -> new JogoDTO(jogodto))
                .toList();
    }
    @Transactional(readOnly = true)
    public List <JogoDTO> listarJogosPorEsporte(Long esporteId){
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        List<Jogo> jogos = jogoRepository.findByEsporte(esporte);

        return jogos.stream()
                .map(jogodto -> new JogoDTO(jogodto))
                .toList();
    }
    public List <JogoDTO> listarJogosPorFase(EnumFase fase) {
        List<Jogo> jogos = jogoRepository.findByFase(fase);


        return jogos.stream()
                .map(jogodto -> new JogoDTO(jogodto))
                .toList();
    }

    //U
    //Apenas o arbitro pode modificar jogos
    public void finalizarJogo(Long id, FinalizarJogoFORM jogo) {

        Jogo jogo1 = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        Equipe equipeA = jogo1.getEquipeA();
        Equipe equipeB = jogo1.getEquipeB();

        jogo1.setPlacarEquipeA(jogo.getPlacarEquipeA());
        jogo1.setPlacarEquipeB(jogo.getPlacarEquipeB());
        jogo1.setFinalizado(true);
        jogo1.setFormaFinalizacao("Padrão");

        //Atribuir um vencedor + pontuação
        if (jogo1.getPlacarEquipeA() > jogo1.getPlacarEquipeB()) {
            jogo1.setVencedor(jogo1.getEquipeA().getNome());
            equipeA.setPontuacao(jogo1.getEquipeA().getPontuacao()+3);
            equipeA.setVitorias(equipeA.getVitorias()+1);
            equipeB.setDerrotas(equipeB.getDerrotas()+1);

        } else if (jogo1.getPlacarEquipeA() < jogo1.getPlacarEquipeB()) {
            jogo1.setVencedor(jogo1.getEquipeB().getNome());
            equipeB.setPontuacao(jogo1.getEquipeB().getPontuacao()+3);
            equipeB.setVitorias(equipeB.getVitorias()+1);
            equipeA.setDerrotas(equipeA.getDerrotas()+1);
        } else {
            jogo1.setVencedor("EMPATE");
            equipeA.setPontuacao(jogo1.getEquipeA().getPontuacao()+1);
            equipeB.setPontuacao(jogo1.getEquipeB().getPontuacao()+1);
        }

        equipeRepository.save(equipeA);
        equipeRepository.save(equipeB);

        jogoRepository.save(jogo1);
    }

    public JogoDTO aplicarWO(Long jogoId, Long equipeVencedoraId) {
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        Equipe equipeA = jogo.getEquipeA();
        Equipe equipeB = jogo.getEquipeB();

        if (!equipeA.getId().equals(equipeVencedoraId) && !equipeB.getId().equals(equipeVencedoraId)) {
            throw new RuntimeException("A equipe vencedora informada não está presente neste jogo.");
        }

        // Atribuindo placar de W.O
        if (equipeA.getId().equals(equipeVencedoraId)) {
            jogo.setPlacarEquipeA(1);
            jogo.setPlacarEquipeB(0);
            jogo.setVencedor(equipeA.getNome());
        } else {
            jogo.setPlacarEquipeA(0);
            jogo.setPlacarEquipeB(1);
            jogo.setVencedor(equipeB.getNome());
        }

        jogo.setFinalizado(true);
        jogo.setFormaFinalizacao("W.O");

        jogoRepository.save(jogo);
        return new JogoDTO(jogo);
    }

        public JogoDTO desfazerWO(Long jogoId) {
            Jogo jogo = jogoRepository.findById(jogoId)
                    .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

            if (!jogo.isFinalizado()) {
                throw new RuntimeException("Este jogo não foi finalizado por WO.");
            }

            // Zerando os dados do WO
            jogo.setPlacarEquipeA(0);
            jogo.setPlacarEquipeB(0);
            jogo.setVencedor(null);
            jogo.setFinalizado(false);
            jogo.setFormaFinalizacao("Não atribuido");


            jogoRepository.save(jogo);
            return new JogoDTO(jogo);
        }

    //D
    public void deletarJogo(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        jogoRepository.deleteById(id);
    }

    public Jogo acharPorId(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        return  jogo;
    }
    public boolean todosJogosFinalizadosPorEsporte(Long esporteId) {
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        List<Jogo> jogos = jogoRepository.findByEsporte(esporte);
        return jogos.stream()
                .allMatch(Jogo::isFinalizado);
    }
    public boolean todosJogosFinalizadosPorEsporteEFase(Long esporteId, EnumFase fase) {
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        List<Jogo> jogos = jogoRepository.findByEsporteAndFase(esporte, fase);
        return jogos.stream().allMatch(Jogo::isFinalizado);
}
    //metodo pra saber se o novo jogo começa antes antes do final de um jogo ja cadastrado (isBefore)
    //e se termina depois do inicio do jogo ja cadastrado (isAfter)
    public boolean haConflitoHorario(LocalDate data, LocalTime novoInicio, LocalTime novoFim) {
        List<Jogo> jogosNoMesmoDia = jogoRepository.findByData(data);

        for (Jogo existente : jogosNoMesmoDia) {
            boolean sobreposicaoHorario =  novoInicio.isBefore(existente.getHoraFim()) && novoFim.isAfter(existente.getHoraInicio());
            if (sobreposicaoHorario) {
                return true;
            }
        }
        return false;
    }

}
