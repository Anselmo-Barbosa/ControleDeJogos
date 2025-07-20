package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.Equipe;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Grupo;
import com.ifs.controlejogos.entities.Jogo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsporteDTO {

    private Long id;
    private String nome;
    private Integer min_jogadores;
    private Integer max_jogadores;


    public EsporteDTO(Esporte esporte){
        this.id = esporte.getId();
        this.nome = esporte.getNome();
        this.min_jogadores = esporte.getMin_jogadores();
        this.max_jogadores = esporte.getMax_jogadores();

    }
}
