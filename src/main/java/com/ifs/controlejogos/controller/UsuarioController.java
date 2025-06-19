package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.Usuario;
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
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping("/list")
    public List<UsuarioDTO> showUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PutMapping("/update/{id}")
    public void updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);

    }

    @GetMapping("/id/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return usuarioService.acharPorId(id);
    }

    @GetMapping("/matricula/{matricula}")
    public UsuarioDTO findByMatricula(@PathVariable String matricula) {
        return usuarioService.acharPorMatricula(matricula);

    }

    @PostMapping("/cadastrar-coordenador")
    public Usuario createCoordenador(@RequestParam String nome, @RequestParam String telefone) {
        return usuarioService.criarCoordernador(nome, telefone);
    }

    @GetMapping("/coordenador/list")
    public List<UsuarioDTO> showCoordenadores() {
        return usuarioService.listarCoodenadores();
    }

    @PutMapping("/coodenador/atribuirtecnico/{id}")
    public void tornarTecnico(@PathVariable Long id) {
        usuarioService.tornarTecnico(id);
    }

    @GetMapping("/tecnicos/list")
    public List<UsuarioDTO> showTecnicos(){
        return usuarioService.listarTecnicos();
    }
}


