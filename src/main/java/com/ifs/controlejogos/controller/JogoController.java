package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Jogo;
import com.ifs.controlejogos.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class JogoController {

    @Autowired
    private JogoService jogoService;

    //C
    @PostMapping()
    public Jogo createJogo(@RequestBody Jogo jogo) {
        return jogoService.criarJogo(jogo);
    }

    //R
    @GetMapping("/list")
    public List<Jogo> showJogos() {
        return jogoService.listarJogos();
    }

    //U
    @PutMapping("/update/{id}")
    public void updateJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
        jogoService.atualizarJogo(id, jogo);
    }

    //D
    @DeleteMapping("/delete/{id}")
    public void deleteJogo(@PathVariable Long id) {
        jogoService.deletarJogo(id);
    }

    @GetMapping("/{id}")
    public Jogo findById(@PathVariable Long id) {
        return jogoService.acharPorId(id);
    }
}
