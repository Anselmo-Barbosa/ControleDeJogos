package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.repository.UsuarioRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String matricula;
    private String telefone;
    private EnumUsuario tipoUsuario;


  public UsuarioDTO(Usuario usuarioDto){
      this.id = usuarioDto.getId();
      this.nome = usuarioDto.getNome();
      this.matricula = usuarioDto.getMatricula();
      this.telefone = usuarioDto.getTelefone();
      this.tipoUsuario = usuarioDto.getTipoUsuario();
  }


}
