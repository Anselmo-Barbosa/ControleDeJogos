package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.CursoDTO;
import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.EnumCurso;
import com.ifs.controlejogos.entities.EnumFase;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.form.CursoFORM;
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
    public CursoDTO criarCurso(@RequestBody CursoFORM cursoFORM) {
        return cursoService.criarCurso(cursoFORM);
    }

    //R
    @GetMapping("/listar")
    public List<CursoDTO> listarCursos() {
        return cursoService.listarCursos();
    }
    @GetMapping("/listar/tipo-curso/{enumCurso}")
    public List<CursoDTO> listarPorTipo(@PathVariable EnumCurso enumCurso){
        return cursoService.listarPorTipo(enumCurso);
    }

    //U
    @PutMapping("/atualizar/{id}")
    public void atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        cursoService.atualizarCurso(id, curso);
    }

    //D
    @DeleteMapping("/deletar/{id}")
    public void deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
    }

    @GetMapping("/{id}")
    public CursoDTO findById(@PathVariable Long id) {
        return cursoService.acharPorId(id);
    }

}





