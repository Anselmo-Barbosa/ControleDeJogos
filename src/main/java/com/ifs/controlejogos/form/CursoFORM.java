package com.ifs.controlejogos.form;

import com.ifs.controlejogos.entities.EnumCurso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoFORM {
    private String nome;

    private EnumCurso tipoCurso;

    private Long campusId;

    private Long coordenadorId;
}
