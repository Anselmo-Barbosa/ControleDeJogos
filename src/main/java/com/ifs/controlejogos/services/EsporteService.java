package com.ifs.controlejogos.services;

import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.repository.EquipeRepository;
import com.ifs.controlejogos.repository.EsporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EsporteService {

    @Autowired
    private EsporteRepository esporteRepository;

    //C
    public Esporte criarEsporte(Esporte esporte) {
        return esporteRepository.save(esporte);
    }

    //R
    @Transactional(readOnly = true)
    public List<Esporte> listarEsportes() {
        return esporteRepository.findAll();
    }

    //U
    public void atualizarEsporte(Long id, Esporte esporte) {
        Optional<Esporte> resultado = esporteRepository.findById(id);

        if (resultado.isPresent()) {
            Esporte esporteExistente = resultado.get();

            esporteExistente.setNome(esporte.getNome());
            esporteExistente.setMin_jogadores(esporte.getMin_jogadores());
            esporteExistente.setMax_jogadores(esporte.getMax_jogadores());

            esporteRepository.save(esporteExistente);
        } else {
            throw new RuntimeException("Esse ID não está vinculado com nenhum esporte!");
        }
    }
    //D
    public void deletarCurso(Long id) {
        Optional<Esporte> resultado = esporteRepository.findById(id);

        if (resultado.isPresent()) {
            esporteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum esporte!");
        }
    }

    public Esporte acharPorId(Long id){
        Optional<Esporte> resultado = esporteRepository.findById(id);

        if(resultado.isPresent()){
            return resultado.get();
        }
        else {
            throw new RuntimeException("Este ID não está vinculado com nenhum esporte!");
        }
    }
}
