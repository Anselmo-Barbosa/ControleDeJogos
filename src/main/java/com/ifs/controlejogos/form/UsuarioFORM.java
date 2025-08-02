package com.ifs.controlejogos.form;


import com.ifs.controlejogos.entities.EnumUsuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFORM {
    @NotNull
    private String matricula;
    @NotNull
    private String nome;
    private String telefone;
    @NotNull
    private String senha;
    private EnumUsuario tipoUsuario = EnumUsuario.ATLETA;
    private Long cursoId;


}
