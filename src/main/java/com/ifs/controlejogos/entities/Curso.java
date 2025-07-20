package com.ifs.controlejogos.entities;

import com.ifs.controlejogos.dto.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;

    @Enumerated(EnumType.STRING)
    private EnumCurso tipoCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campus")
    private Campus campus;

    @OneToOne
    @JoinColumn(name = "id_coordenador")
    private Usuario coordenador;
}
