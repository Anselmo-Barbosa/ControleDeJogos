package com.ifs.controlejogos.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String matricula;
    @NotNull
    private String nome;
    private String telefone;
    @NotNull
    private String senha;

    @Enumerated(EnumType.STRING)
    private EnumUsuario tipoUsuario = EnumUsuario.ATLETA;

    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    @ManyToMany
    @JoinTable(name = "atleta_equipe",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_equipe"))
    private List<Equipe> equipes =  new ArrayList<>();

}


