package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.Usuario;

public class UsuarioMinDTO {
    private String matricula;
    private String nome;

    public UsuarioMinDTO(){
    }

    public UsuarioMinDTO(Usuario usuarioMinDto){
        this.nome=usuarioMinDto.getNome();
        this.matricula = usuarioMinDto.getMatricula();

    }
}

