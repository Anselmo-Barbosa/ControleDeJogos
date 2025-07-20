package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordenadorDTO {
    private Long id;
    private String nome;
    private String matricula;
    private String telefone;
    private EnumUsuario tipoUsuario;
    private String curso ;


    public CoordenadorDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.matricula = usuario.getMatricula();
        this.telefone = usuario.getTelefone();
        this.tipoUsuario = usuario.getTipoUsuario();
        if(usuario.getCurso() != null) {
            this.curso = usuario.getCurso().getNome();
        }
    }
}
