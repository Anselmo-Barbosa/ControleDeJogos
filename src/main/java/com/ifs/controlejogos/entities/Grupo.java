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
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;


    @ManyToOne
    @JoinColumn(name = "id_esporte")
    private Esporte esporte;


    @OneToMany(mappedBy = "grupo")
    private List<Equipe> equipes;

}
