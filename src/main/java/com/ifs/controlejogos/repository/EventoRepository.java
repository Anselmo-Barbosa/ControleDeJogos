package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
}
