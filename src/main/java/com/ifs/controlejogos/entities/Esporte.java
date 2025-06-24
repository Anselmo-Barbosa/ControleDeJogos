package com.ifs.controlejogos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Esporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer min_jogadores;
    private Integer max_jogadores;

    @OneToMany(mappedBy = "esporte")
    private List<Jogo> jogos;

    @OneToMany(mappedBy = "esporte")
    private List<Equipe> equipes;

    @OneToMany(mappedBy = "esporte")
    private List<Grupo> grupos;
}
