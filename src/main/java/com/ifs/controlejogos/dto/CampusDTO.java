package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CampusDTO {
    private Long id;
    private String nome;
    private String cidade;
    private String estado;


    private List<Long> cursoIds;

    public CampusDTO(Campus campus){
        this.id = campus.getId();
        this.nome = campus.getNome();
        this.cidade = campus.getCidade();
        this.estado = campus.getEstado();

        this.cursoIds = campus.getCursos().stream()
                .map(cursos -> cursos.getId())
                .toList();
    }
}
