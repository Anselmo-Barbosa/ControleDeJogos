package com.ifs.controlejogos.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import lombok.*;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
