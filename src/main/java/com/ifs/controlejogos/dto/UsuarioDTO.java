package com.ifs.controlejogos.dto;


import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import lombok.*;


import java.util.List;

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
    private String curso;
    private List<EquipeMinDTO> equipes;


  public UsuarioDTO(Usuario usuarioDto){
      this.id = usuarioDto.getId();
      this.nome = usuarioDto.getNome();
      this.matricula = usuarioDto.getMatricula();
      this.telefone = usuarioDto.getTelefone();
      this.tipoUsuario = usuarioDto.getTipoUsuario();
      if(usuarioDto.getCurso() != null) {
          this.curso = usuarioDto.getCurso().getNome();
      }
      this.equipes = usuarioDto.getEquipes().stream()
              .map(equipe -> new EquipeMinDTO(equipe))
              .toList();
  }

}
