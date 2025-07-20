package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.EnumCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {

Optional<Campus> findByNome(String nome);
List<Curso> findByTipoCurso(EnumCurso enumCurso);

}
