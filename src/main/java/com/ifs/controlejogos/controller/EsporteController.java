package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Esporte;
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
    public Esporte createEsporte(@RequestBody Esporte esporte) {
        return esporteService.criarEsporte(esporte);
    }

    @GetMapping("/list")
    public List<Esporte> showEsportes() {
        return esporteService.listarEsportes();
    }

    //U
    @PutMapping("/update/{id}")
    public void updateEsporte(@PathVariable Long id, @RequestBody Esporte esporte) {
        esporteService.atualizarEsporte(id, esporte);
    }

    //D
    @DeleteMapping("/delete/{id}")
    public void deleteEsporte(@PathVariable Long id) {
        esporteService.deletarCurso(id);
    }

    @GetMapping("/{id}")
    public Esporte findById(@PathVariable Long id) {
        return esporteService.acharPorId(id);
    }
}
