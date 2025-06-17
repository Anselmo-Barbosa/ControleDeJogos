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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matricula;
    private String nome;
    private String telefone;
    private String senha;

    @Enumerated(EnumType.STRING)
    private EnumUsuario tipoUsuario;

}


