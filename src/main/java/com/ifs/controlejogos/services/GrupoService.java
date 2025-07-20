package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.GrupoDTO;
import com.ifs.controlejogos.entities.Equipe;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Grupo;
import com.ifs.controlejogos.repository.EquipeRepository;
import com.ifs.controlejogos.repository.EsporteRepository;
import com.ifs.controlejogos.repository.GrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private EsporteRepository esporteRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    @Transactional
    public List<GrupoDTO> gerarGrupos(Long esporteId) {
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        List<Equipe> equipes = equipeRepository.findByEsporte(esporte);


        //Embaralha as equipes dentro do esporte
        Collections.shuffle(equipes);


        List<Grupo> gruposCriados = new ArrayList<>();
        int indice = 0;
        int contadorGrupo = 1;

        // Enquanto ainda houver equipes para alocar
        while (indice < equipes.size()) {
            int restante = equipes.size() - indice;
            int tamanhoGrupo;

            // Lógica para decidir o tamanho ideal do próximo grupo
            if (restante == 10) {
                tamanhoGrupo = 3;
            } else if (restante == 8) {
                tamanhoGrupo = 4;
            } else if (restante == 5) {
                tamanhoGrupo = 5;
            } else if (restante == 4) {
                tamanhoGrupo = 4;
            } else if (restante >= 3) {
                tamanhoGrupo = 3;
            } else {
                throw new RuntimeException("Não é possível formar grupos com " + restante + " equipes restantes.");
            }

            Grupo grupo = new Grupo();
            grupo.setNome("Grupo " + contadorGrupo++);
            //Cada equipe precisa de um grupo ja persistido no banco pra ser associada
            grupo = grupoRepository.save(grupo);

            List<Equipe> subGrupo = new ArrayList<>();

            //Atribuindo o grupo em cada equipe
            for (int i = 0; i < tamanhoGrupo && indice < equipes.size(); i++) {
                Equipe equipe = equipes.get(indice);

                subGrupo.add(equipe);

                // Definindo o grupo na equipe (outro lado da relação)
                equipe.setGrupo(grupo);
                equipeRepository.save(equipe);
                indice++;
            }
            grupo.setEquipes(subGrupo);
            grupo.setEsporte(esporte);

            //Necessario pra retornar
            gruposCriados.add(grupo);

            //atualizando os campos de esporte (outro lado da relação)
            esporte.getGrupos().add(grupo);

        }
        esporteRepository.save(esporte);

        return gruposCriados
                .stream()
                .map(grupo -> new GrupoDTO(grupo))
                .toList();
    }

    public List<GrupoDTO> listarGrupos() {
        return grupoRepository.findAll()
                .stream()
                .map(grupo -> new GrupoDTO(grupo))
                .toList();
    }

    public List<GrupoDTO> listarGruposPorEsporte(Long esporteId) {
        Esporte esporte = esporteRepository.findById(esporteId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        return grupoRepository.findByEsporte(esporte)
                .stream()
                .map(grupo -> new GrupoDTO(grupo))
                .toList();
    }
    public void deletarGrupo(Long grupoId){
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        grupoRepository.deleteById(grupoId);
    }
}
