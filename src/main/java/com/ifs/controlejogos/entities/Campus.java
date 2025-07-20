package com.ifs.controlejogos.entities;

import com.ifs.controlejogos.dto.CursoDTO;
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
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    private String cidade;
    private String estado;


    @OneToMany(mappedBy = "campus",fetch = FetchType.LAZY)
    private List<Curso> cursos =  new ArrayList<>();


}
