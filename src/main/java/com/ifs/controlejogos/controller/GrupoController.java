package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.GrupoDTO;
import com.ifs.controlejogos.entities.Equipe;
import com.ifs.controlejogos.entities.Grupo;
import com.ifs.controlejogos.repository.EquipeRepository;
import com.ifs.controlejogos.repository.GrupoRepository;
import com.ifs.controlejogos.services.EquipeService;
import com.ifs.controlejogos.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    public GrupoService grupoService;


    @PostMapping("/{id}")
    public List<GrupoDTO> gerarGrupos(@PathVariable Long id) {
        return grupoService.gerarGrupos(id);
    }

    @GetMapping("/listar")
    public List<GrupoDTO> listarGrupos() {
        return grupoService.listarGrupos();
    }
    @GetMapping("/listar/{id}")
    public List<GrupoDTO> listarGruposPorEsporte(@PathVariable Long id) {
        return grupoService.listarGruposPorEsporte(id);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarGrupo(@PathVariable Long id){
        grupoService.deletarGrupo(id);
    }
}
