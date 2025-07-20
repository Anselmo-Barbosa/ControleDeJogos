package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.Equipe;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Grupo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    private Long id;

    private String nome;

    private List<EquipeMinDTO> equipesDto;

    public GrupoDTO (Grupo grupo){
        this.id = grupo.getId();
        this.nome = grupo.getNome();
        this.equipesDto = grupo.getEquipes()
                .stream()
                .map(equipe -> new EquipeMinDTO(equipe))
                .toList();
    }
}
