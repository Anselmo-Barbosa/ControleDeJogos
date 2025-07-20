package com.ifs.controlejogos.form;

import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Evento;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsporteFORM {
    @NotNull
    @Column(unique = true)
    private String nome;
    @NotNull
    private Integer min_jogadores;
    @NotNull
    private Integer max_jogadores;
    private Long eventoId;

    //metodo auxiliar de conversão FORM x Entidade
    public Esporte toEsporte(Evento evento){
         Esporte esporte = new Esporte();
         esporte.setNome(nome);
         esporte.setMin_jogadores(min_jogadores);
         esporte.setMax_jogadores(max_jogadores);
         esporte.setEvento(evento);
         return esporte;
    }
}
