package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus,Long> {
    Optional<Campus> findByNome(String nome);
}
