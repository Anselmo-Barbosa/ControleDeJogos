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
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String data;
    private String hora;
    private EnumCurso tipoEvento;

    @OneToMany(mappedBy = "evento")
    private List<Jogo> jogos;

    @ManyToOne
    @JoinColumn(name = "id_campus")
    private Campus campus;
}
