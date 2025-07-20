package com.ifs.controlejogos.form;

import com.ifs.controlejogos.entities.EnumFase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProximaFaseFORM {
    private EnumFase faseAtual;
    private ConfigJogoFORM config;
}
