package com.ifs.controlejogos.repository;

import com.ifs.controlejogos.entities.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository <Jogo,Long> {

    //Rotorna um boolean diretamente
    //COUNT(j) Conta quantos jogos foram encontrados com os critérios.
    //> 0 se encontrou pelo menos 1 jogo que conflita com o horário informado. se sim(true), se não(false)
    //"FROM Jogo j' a consulta está buscando registros na tabela Jogo, usando um apelido j.
    //"WHERE j.data = :data" faz a filtragem apenas do dia especificado
    @Query("SELECT CASE WHEN COUNT(j) > 0 THEN true ELSE false END " +
            "FROM Jogo j WHERE j.data = :data " +
            //Jogo existente (j) começa antes ou durante do jogo a ser criado E termina depois ou durante o jogo
            // existente começa
            "AND ( (j.horaInicio <= :horaFim AND j.horaFim >= :horaInicio) )")
    boolean existsByDataAndHora(@Param("data") String data, @Param("horaInicio") String horaInicio,
                                @Param("horaFim") String horaFim);

}
