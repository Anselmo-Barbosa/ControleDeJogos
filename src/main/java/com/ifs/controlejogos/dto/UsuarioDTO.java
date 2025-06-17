package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class UsuarioDTO {
    private Long id;
    private String nome;
    private Long matricula;
    private String telefone;
    private EnumUsuario tipoUsuario;

    @Autowired
    private UsuarioRepository usuarioRepository;

  public UsuarioDTO(){
  }

  public UsuarioDTO(Usuario usuarioDto){
      this.id = usuarioDto.getId();
      this.nome = usuarioDto.getNome();
      this.matricula = usuarioDto.getMatricula();
      this.telefone = usuarioDto.getTelefone();
      this.tipoUsuario = usuarioDto.getTipoUsuario();
  }


}
