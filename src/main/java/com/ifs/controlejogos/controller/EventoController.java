package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.EventoDTO;
import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.form.EventoFORM;
import com.ifs.controlejogos.services.CampusService;
import com.ifs.controlejogos.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public EventoDTO criarEvento(@RequestBody EventoFORM form) {
        return eventoService.criarEvento(form);
    }
    @GetMapping("/listar")
    public List<EventoDTO> listarEventos() {
        return eventoService.listarEventos();
    }
    @PutMapping("/finalizar/{id}")
    public void finalizarEvento(@PathVariable Long id){
        eventoService.finalizarEvento(id);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarEvento(@PathVariable Long id){
        eventoService.deletarEvento(id);
    }
}

