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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private EnumCurso tipoCurso;

    @ManyToOne()
    @JoinColumn(name = "id_campus")
    private Campus campus;

    @OneToMany(mappedBy = "curso")
    private List<Equipe> equipes;

}
