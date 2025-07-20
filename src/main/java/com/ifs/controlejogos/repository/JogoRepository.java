package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.EnumFase;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Grupo;
import com.ifs.controlejogos.entities.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository <Jogo,Long> {
    List<Jogo> findByGrupo(Grupo grupo);
    List<Jogo> findByEsporte(Esporte esporte);
    List<Jogo> findByEsporteAndFase(Esporte esporte, EnumFase fase);
    List<Jogo> findByData(LocalDate data);
    List<Jogo> findByFase(EnumFase fase);
}
