package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDTO {

    private Long id;
    private String nome;
    private Integer pontuacao;
    private Integer vitorias;
    private Integer derrotas;

    private String tecnicoNome;

    private Long cursoId;

    private Long esporteId;

    private Long grupoId;

    @Transient
    private List<Long> usuariosId;

    public EquipeDTO(Equipe equipe){
        this.id = equipe.getId();
        this.nome = equipe.getNome();
        this.vitorias = equipe.getVitorias();
        this.derrotas = equipe.getDerrotas();
        this.cursoId = equipe.getCurso().getId();
        this.esporteId = equipe.getEsporte().getId();
        if (equipe.getGrupo() != null) {
            this.grupoId = equipe.getGrupo().getId();
        }
        if(equipe.getTecnico() != null) {
            this.tecnicoNome = equipe.getTecnico().getNome();
        }
        if(equipe.getPontuacao() != null){
            this.pontuacao = equipe.getPontuacao();
        }
        this.usuariosId = equipe.getUsuarios().stream()
                .filter(usuario -> usuario.getTipoUsuario() == EnumUsuario.ATLETA)
                .map(Usuario::getId)
                .toList();
    }
}
