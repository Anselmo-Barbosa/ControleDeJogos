package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeMinDTO {

    private Long id;
    private String nome;

    private String nomeCurso;
    private Integer pontuacao;

    public EquipeMinDTO(Equipe equipe){
        this.id = equipe.getId();
        this.nome = equipe.getNome();
        this.nomeCurso = equipe.getCurso().getNome();
        this.pontuacao = equipe.getPontuacao();
    }
}
