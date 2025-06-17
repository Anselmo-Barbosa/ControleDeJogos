package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,Long> {
}
