package com.ifs.controlejogos.controller;

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
    public Campus createCampus(@RequestBody Campus campus) {
        return campusService.criarCampus(campus);
    }

    //R
    @GetMapping("/list")
    public List<Campus> showCampus() {
        return campusService.listarCampus();
    }

    //U
    @PutMapping("/update/{id}")
    public void updateCampus(@PathVariable Long id, @RequestBody Campus campus) {
        campusService.atualizarCampus(id, campus);
    }

    //D
    @DeleteMapping("/delete/{id}")
    public void deleteCampus(@PathVariable Long id) {
        campusService.deletarCampus(id);
    }

    @GetMapping("/{id}")
    public Campus findById(@PathVariable Long id) {
        return campusService.acharPorId(id);
    }

}
