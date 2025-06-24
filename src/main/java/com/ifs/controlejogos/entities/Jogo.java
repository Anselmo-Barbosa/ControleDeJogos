package com.ifs.controlejogos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String horaInicio;
    private String horaFim;
    private Integer placarEquipeA;
    private Integer placarEquipeB;


    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_esporte")
    private Esporte esporte;

    @ManyToOne()
    @JoinColumn(name = "id_campus")
    private Campus campus;


    //Mapeamento unidirecional, sem precisar mapear Jogo em equipe (So quero acessar os
    // jogos a partir da equipe)
    @ManyToOne
    @JoinColumn(name = "id_equipe_a")
    private Equipe equipeA;

    @ManyToOne
    @JoinColumn(name = "id_equioe_b")
    private Equipe equipeB;





}
