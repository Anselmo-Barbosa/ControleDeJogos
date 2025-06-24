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
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cidade;
    private String estado;

    @OneToMany(mappedBy = "campus")
    private List<Evento> eventos;

    @OneToMany(mappedBy = "campus")
    private List<Jogo> jogos;

    @OneToMany(mappedBy = "campus")
    private List<Curso> cursos;

}
