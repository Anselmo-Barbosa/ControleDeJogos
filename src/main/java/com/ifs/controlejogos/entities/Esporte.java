package com.ifs.controlejogos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Esporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String nome;
    @NotNull
    private Integer min_jogadores;
    @NotNull
    private Integer max_jogadores;

    @OneToMany(mappedBy = "esporte")
    private List<Jogo> jogos = new ArrayList<>();

    @OneToMany(mappedBy = "esporte")
    private List<Equipe> equipes = new ArrayList<>();

    @OneToMany(mappedBy = "esporte")
    private List<Grupo> grupos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

}
