package com.ifs.controlejogos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "recebeu_bye")
    private Boolean recebeuBye = false;
    private Integer vitorias = 0;
    private Integer derrotas = 0;

    @Column(nullable = false)
    private Integer pontuacao = 0;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_esporte")
    private Esporte esporte;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToMany(mappedBy = "equipes",fetch = FetchType.EAGER)
    private List<Usuario> usuarios = new ArrayList<>() ;

    @OneToOne
    @JoinColumn(name = "id_tecnico")
    private Usuario tecnico;

    @Transient
    private Long cursoId;

    @Transient
    private Long EsporteId;

    @Transient
    private List<Long> usuariosId;
}
