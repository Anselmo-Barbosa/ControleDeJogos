package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.EnumFase;
import com.ifs.controlejogos.entities.Jogo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoDTO {

    private Long id;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Long equipe_a_id;
    private Long equipe_b_id;
    private Integer placarEquipeA;
    private Integer placarEquipeB;
    private String vencedor;
    private boolean finalizado;
    private String formaFinalizacao;
    private EnumFase fase;

    private Long esporteId;



    public JogoDTO(Jogo jogo){
        this.id = jogo.getId();
        this.esporteId = jogo.getEsporte().getId();
        this.data = jogo.getData();
        this.horaInicio = jogo.getHoraInicio();
        this.horaFim = jogo.getHoraFim();
        this.equipe_a_id = jogo.getEquipeA().getId();
        this.equipe_b_id = jogo.getEquipeB().getId();
        this.placarEquipeA = jogo.getPlacarEquipeA();
        this.placarEquipeB = jogo.getPlacarEquipeB();
        this.vencedor = jogo.getVencedor();
        this.finalizado = jogo.isFinalizado();
        this.formaFinalizacao = jogo.getFormaFinalizacao();
        this.fase = jogo.getFase();

    }
}
