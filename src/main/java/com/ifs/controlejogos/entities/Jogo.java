package com.ifs.controlejogos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_jogo")
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer placarEquipeA;
    private Integer placarEquipeB;
    private String vencedor;
    private boolean finalizado;
    private String formaFinalizacao = "Não atribuido";

    @Enumerated(EnumType.STRING)
    private EnumFase fase;

    @ManyToOne
    @JoinColumn(name = "id_esporte")
    private Esporte esporte;


    //Mapeamento unidirecional, sem precisar mapear Jogo em equipe (So quero acessar os
    // jogos a partir da equipe)
    @ManyToOne
    @JoinColumn(name = "id_equipe_a")
    private Equipe equipeA;

    @ManyToOne
    @JoinColumn(name = "id_equipe_b")
    private Equipe equipeB;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    public Equipe getVencedorEquipe() {
        if (placarEquipeA > placarEquipeB){
            return equipeA;
        }
        else if (placarEquipeB > placarEquipeA) {
            return equipeB;
        }
        //se empatarem no placar, vão desempatar na pontuação da classificatoria
        else {
            if(equipeA.getPontuacao()>equipeB.getPontuacao()){
                return equipeA;
            }
            else if(equipeB.getPontuacao()>equipeA.getPontuacao()){
                return equipeB;
            }
        }
        return null;
    }
}
