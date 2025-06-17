package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository <Grupo, Long> {
}
