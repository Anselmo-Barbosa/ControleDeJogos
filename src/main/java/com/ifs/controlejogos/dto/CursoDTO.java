package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Long id;
    private String nome;

    private EnumCurso tipoCurso;

    private Long campusId;

    private Long coordenadorId;

    public CursoDTO (Curso curso){
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.tipoCurso = curso.getTipoCurso();

        if(curso.getCampus() != null) {
            this.campusId = curso.getCampus().getId();
        }
        this.coordenadorId = curso.getCoordenador().getId();
    }
}
