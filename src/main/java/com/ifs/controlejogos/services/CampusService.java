package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.CampusDTO;
import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.repository.CampusRepository;
import com.ifs.controlejogos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {

    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private CursoRepository cursoRepository;


    //C
    public CampusDTO criarCampus(Campus campus){
        campusRepository.save(campus);

        return new CampusDTO(campus);
    }

    //R
    @Transactional(readOnly = true)
    public List<CampusDTO> listarCampus() {
        List<Campus> listacampus = campusRepository.findAll();

        return listacampus.stream()
                .map(campus -> new CampusDTO(campus))
                .toList();
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
        Campus resultado = campusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campus não encontrado"));

            campusRepository.deleteById(id);
    }

    public CampusDTO acharPorId(Long id){
       Campus resultado = campusRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Campus não encontrado"));

            return new CampusDTO(resultado);

    }
}
