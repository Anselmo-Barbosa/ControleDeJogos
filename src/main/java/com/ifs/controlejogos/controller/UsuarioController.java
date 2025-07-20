package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.CoordenadorDTO;
import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.form.CoordenadorFORM;
import com.ifs.controlejogos.form.UsuarioFORM;
import com.ifs.controlejogos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public UsuarioDTO criarUsuario(@RequestBody UsuarioFORM usuarioFORM) {
        return usuarioService.criarUsuario(usuarioFORM);
    }

    @GetMapping("/listar")
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/listar/por-tipo/{tipoUsuario}")
    public List<UsuarioDTO> listarUsuariosPorTipo(@PathVariable EnumUsuario tipoUsuario) {
        return usuarioService.listarUsuariosPorTipo(tipoUsuario);
    }

    @PutMapping("/atualizar/{id}")
    public void atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);

    }

    @GetMapping("/{id}")
    public UsuarioDTO acharPorId(@PathVariable Long id) {
        return usuarioService.acharPorId(id);
    }

    @GetMapping("/matricula/{matricula}")
    public UsuarioDTO acharPelaMatricula(@PathVariable String matricula) {
        return usuarioService.acharPorMatricula(matricula);

    }

    @PostMapping("/cadastrar-coordenador")
    public CoordenadorDTO criarCoordenador(@RequestBody CoordenadorFORM coordenadorFORM) {
        return usuarioService.criarCoordernador(coordenadorFORM);
    }

    @PutMapping("/coodenador/atribuirtecnico/{id}")
    public void tornarTecnico(@PathVariable Long id) {
        usuarioService.tornarTecnico(id);
    }

}


