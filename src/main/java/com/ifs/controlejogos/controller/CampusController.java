package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.CampusDTO;
import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.services.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    //C
    @PostMapping()
    public CampusDTO criarCampus(@RequestBody Campus campus) {
        return campusService.criarCampus(campus);
    }

    //R
    @GetMapping("/listar")
    public List<CampusDTO> listarCampus() {
        return campusService.listarCampus();
    }

    //U
    @PutMapping("/atualizar/{id}")
    public void atualizarCampus(@PathVariable Long id, @RequestBody Campus campus) {
        campusService.atualizarCampus(id, campus);
    }

    //D
    @DeleteMapping("/deletar/{id}")
    public void deletarCampus(@PathVariable Long id) {
        campusService.deletarCampus(id);
    }

    @GetMapping("/{id}")
    public CampusDTO acharPorId(@PathVariable Long id) {
        return campusService.acharPorId(id);
    }

}
