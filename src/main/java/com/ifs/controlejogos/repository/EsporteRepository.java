package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EsporteRepository extends JpaRepository<Esporte,Long> {
    Optional<Campus> findByNome(String nome);
}
