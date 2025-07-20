package com.ifs.controlejogos.form;
import com.ifs.controlejogos.entities.EnumCurso;
import com.ifs.controlejogos.entities.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoFORM {
    private String nome;
    private String local;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private EnumCurso tipoEvento;
    private Long eventoId;


}
