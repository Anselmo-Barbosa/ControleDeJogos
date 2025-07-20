package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.EsporteDTO;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.form.EsporteFORM;
import com.ifs.controlejogos.repository.EsporteRepository;
import com.ifs.controlejogos.services.EsporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esportes")
public class EsporteController {

    @Autowired
    private EsporteService esporteService;

    @PostMapping()
    public EsporteDTO criarEsporte(@RequestBody EsporteFORM esporte) {
        return esporteService.criarEsporte(esporte);
    }

    @GetMapping("/listar")
    public List<EsporteDTO> listarEsportes() {
        return esporteService.listarEsportes();
    }

    //U
    @PutMapping("/atualizar/{id}")
    public void atualizarEsporte(@PathVariable Long id, @RequestBody Esporte esporte) {
        esporteService.atualizarEsporte(id, esporte);
    }

    //D
    @DeleteMapping("/deletar/{id}")
    public void deletarEsporte(@PathVariable Long id) {
        esporteService.deletarEsporte(id);
    }

    @GetMapping("/{id}")
    public EsporteDTO acharPorId(@PathVariable Long id) {
        return esporteService.acharPorId(id);
    }
}
