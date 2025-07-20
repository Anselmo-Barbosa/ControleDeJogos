package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Equipe;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository <Equipe,Long> {

    boolean existsByEsporteAndCurso(Esporte esporte, Curso curso);

    List<Equipe> findByEsporte(Esporte esporte);
    List<Equipe> findByEsporteAndRecebeuByeTrue(Esporte esporte);

}
