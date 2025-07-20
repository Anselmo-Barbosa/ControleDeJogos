package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.EnumCurso;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Long id;
    private String nome;
    private String local;
    private LocalDate dataInicio;
    private String dataFim;
    private String descricao;
    private Boolean finalizado;
    private EnumCurso tipoCurso;
    private List<EsporteEmEventoDTO> esportes = new ArrayList<>();

    public EventoDTO(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.local = evento.getLocal();
        this.dataInicio = evento.getDataInicio();
        this.descricao = evento.getDescricao();
        this.finalizado = evento.getFinalizado();
        this.tipoCurso = evento.getTipoEvento();

        for(Esporte esporte: evento.getEsportes()) {
            esportes.add(new EsporteEmEventoDTO(esporte));
        }
        if (evento.getDataFim() != null) {
            this.dataFim = evento.getDataFim().toString();
        } else {
            this.dataFim = "Em andamento";
        }
    }
}
