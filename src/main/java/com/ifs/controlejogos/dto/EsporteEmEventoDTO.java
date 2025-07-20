package com.ifs.controlejogos.dto;

import com.ifs.controlejogos.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsporteEmEventoDTO {
    private String nome;
    private List<Long> gruposId = new ArrayList<>();;
    private List<Long> jogosId = new ArrayList<>();;
    private List<String> equipesNomes = new ArrayList<>();
    private String equipeVencedora = "Indefinido";

    public EsporteEmEventoDTO(Esporte esporte){
        this.nome = esporte.getNome();
        if (esporte.getGrupos() != null) {
            this.gruposId = esporte.getGrupos().stream()
                    .map(Grupo::getId)
                    .toList();
        }
        if (esporte.getJogos() != null) {
            this.jogosId = esporte.getJogos().stream()
                    .map(Jogo::getId)
                    .toList();
        }
        if (esporte.getEquipes() != null) {
            this.equipesNomes = esporte.getEquipes().stream()
                    .map(Equipe::getNome)
                    .toList();
        }
        for (Jogo jogo : esporte.getJogos()) {
            if (jogo.getFase() == EnumFase.FINAL && jogo.isFinalizado()) {
                this.equipeVencedora = jogo.getVencedor();
            }
        }
    }
}

