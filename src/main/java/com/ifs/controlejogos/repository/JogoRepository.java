package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository <Jogo,Long> {
}
