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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String email;
    private String nome;
    private String telefone;
    private String senha;

    @Enumerated(EnumType.STRING)
    private EnumUsuario tipoUsuario = EnumUsuario.ATLETA;

    @ManyToMany()
    @JoinTable(name = "atleta_equipe",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_equipe"))
    private List<Equipe> equipes;


}


