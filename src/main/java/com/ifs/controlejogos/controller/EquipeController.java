package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.EquipeDTO;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Equipe;
import com.ifs.controlejogos.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/{tecnicoId}")
    public EquipeDTO criarEquipe(@PathVariable Long tecnicoId,@RequestBody Equipe equipe) {
        return equipeService.criarEquipe(tecnicoId,equipe);
    }
    //R
    @GetMapping("/listar")
    public List<EquipeDTO> listarEquipes() {
        return equipeService.listarEquipes();
    }

    //U
    @PutMapping("/atualizar/{id}")
    public void atualizarEquipe(@PathVariable Long id, @RequestBody Equipe equipe) {
        equipeService.atualizarEquipe(id, equipe);
    }
    //D
    @DeleteMapping("/deletar/{id}")
    public void deletarEquipe(@PathVariable Long id) {
        equipeService.deletarEquipe(id);
    }

    @GetMapping("/{id}")
    public Equipe AcharPorId(@PathVariable Long id) {
        return equipeService.acharPorId(id);
    }
}

