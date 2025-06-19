package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    //C
    @PostMapping()
    public Curso createUsuario(@RequestBody Curso curso) {
        return cursoService.criarCurso(curso);
    }

    //R
    @GetMapping("/list")
    public List<Curso> showCursos() {
        return cursoService.listarCursos();
    }

    //U
    @PutMapping("/update/{id}")
    public void updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        cursoService.atualizarCurso(id, curso);
    }

    //D
    @DeleteMapping("/delete/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Long id) {
        return cursoService.acharPorId(id);
    }

    @GetMapping("/integrado/list")
    public List<Curso> showCursosInt() {
       return cursoService.listarCursosIntegrados();
    }

    @GetMapping("/tecnico/list")
    public List<Curso> showCursosTec() {
        return cursoService.listarCursosTecnicos();
    }

    @GetMapping("/superior/list")
    public List<Curso> showCursosSup() {
        return cursoService.listarCursosSuperiores();
    }

}





