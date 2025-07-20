package com.ifs.controlejogos.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinalizarJogoFORM {
    private Integer placarEquipeA;
    private Integer placarEquipeB;
}
