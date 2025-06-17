package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus,Long> {
}
