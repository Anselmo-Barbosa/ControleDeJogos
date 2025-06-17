package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsporteRepository extends JpaRepository<Esporte,Long> {
}
