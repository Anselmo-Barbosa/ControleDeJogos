package com.ifs.controlejogos.services;

import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampusService {

    @Autowired
    private CampusRepository campusRepository;


    //C
    public Campus criarCampus(Campus campus) {
        return campusRepository.save(campus);
    }

    //R
    @Transactional(readOnly = true)
    public List<Campus> listarCampus() {
        return campusRepository.findAll();
    }

    //U
    public void atualizarCampus(Long id, Campus campus) {
        Optional<Campus> resultado = campusRepository.findById(id);

        if (resultado.isPresent()) {
            Campus campusExistente = resultado.get();

            campusExistente.setNome(campus.getNome());
            campusExistente.setCidade(campus.getCidade());
            campusExistente.setEstado(campus.getEstado());

            campusRepository.save(campusExistente);
        } else {
            throw new RuntimeException("Esse ID não está vinculado com nenhum campus!");
        }
    }

    //D
    public void deletarCampus(Long id) {
        Optional<Campus> resultado = campusRepository.findById(id);

        if (resultado.isPresent()) {
            campusRepository.deleteById(id);
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum campus!");
        }
    }
    public Campus acharPorId(Long id){
        Optional<Campus> resultado = campusRepository.findById(id);

        if (resultado.isPresent()){
            return resultado.get();
        }
        else {
            throw new RuntimeException("Este ID não está vinculado com nenhum campus!");
        }
    }
}
