package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository <Equipe,Long> {
}
