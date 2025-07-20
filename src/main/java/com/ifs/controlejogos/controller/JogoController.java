package com.ifs.controlejogos.controller;

import com.ifs.controlejogos.dto.JogoDTO;
import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.EnumFase;
import com.ifs.controlejogos.entities.Jogo;
import com.ifs.controlejogos.form.ConfigJogoFORM;
import com.ifs.controlejogos.form.FinalizarJogoFORM;
import com.ifs.controlejogos.form.ProximaFaseFORM;
import com.ifs.controlejogos.services.EliminatoriasService;
import com.ifs.controlejogos.services.JogoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;
    @Autowired
    private EliminatoriasService eliminatoriasService;

    //C
    @PostMapping("/gerar/{grupoId}")
    public List<JogoDTO> createJogo(@PathVariable Long grupoId, @RequestBody ConfigJogoFORM config) {
        return jogoService.gerarJogos(grupoId,config);
    }
    @PostMapping("/gerar/eliminatorias/{esporteId}")
    public List<JogoDTO> gerarFaseEliminatoria(@PathVariable Long esporteId, @RequestBody ConfigJogoFORM config) {
        return eliminatoriasService.gerarFaseEliminatoria(esporteId, config);
    }
    @PostMapping("/{esporteId}/eliminatorias/proxima-fase")
    public List<JogoDTO> gerarProximaFase(@PathVariable Long esporteId, @RequestBody ProximaFaseFORM  faseForm) {
        return eliminatoriasService.gerarProximaFase(esporteId, faseForm.getFaseAtual(), faseForm.getConfig());
    }
    //R
    @GetMapping("/list/porgrupo/{grupoId}")
    public List<JogoDTO> listarJogosPorGrupo(@PathVariable Long grupoId) {
        return jogoService.listarJogosPorGrupo(grupoId);
    }
    @GetMapping("/list/poresporte/{esporteId}")
    public List<JogoDTO> listarJogosPorEsporte(@PathVariable Long esporteId) {
        return jogoService.listarJogosPorEsporte(esporteId);
    }
    @GetMapping("/list/porfase/{fase}")
    public List<JogoDTO> listarJogosPorFase(@PathVariable EnumFase fase) {
        return jogoService.listarJogosPorFase(fase);
    }
    //U
    @PutMapping("/finalizar/{Id}")
    public void finalizarJogo(@PathVariable Long Id, @RequestBody FinalizarJogoFORM jogo){
        jogoService.finalizarJogo(Id,jogo);
    }
    @PutMapping("/{jogoId}/w.o/{equipeId}")
    public JogoDTO aplicarWO(@PathVariable Long jogoId,@PathVariable Long equipeId) {
        JogoDTO jogoDTO = jogoService.aplicarWO(jogoId, equipeId);
        return jogoDTO;
    }
    @PutMapping("/-w.o/{jogoId}")
    public JogoDTO desfazerWO(@PathVariable Long jogoId) {
        JogoDTO jogoDTO = jogoService.desfazerWO(jogoId);
        return jogoDTO;
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
