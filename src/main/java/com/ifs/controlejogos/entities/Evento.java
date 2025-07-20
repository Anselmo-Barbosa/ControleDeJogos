package com.ifs.controlejogos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private EnumCurso tipoEvento;
    private String local;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private Boolean finalizado;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Esporte> esportes = new ArrayList<>();

}
