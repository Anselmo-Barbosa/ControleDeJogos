package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.EventoDTO;
import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Evento;
import com.ifs.controlejogos.form.EventoFORM;
import com.ifs.controlejogos.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;


    public EventoDTO criarEvento(EventoFORM form) {
        Evento evento = new Evento();

        evento.setNome(form.getNome());
        evento.setLocal(form.getLocal());
        evento.setDataInicio(form.getDataInicio());
        evento.setDataFim(form.getDataFim());
        evento.setDescricao(form.getDescricao());
        evento.setFinalizado(false);
        evento.setTipoEvento(form.getTipoEvento());

        Evento salvo = eventoRepository.save(evento);
        return new EventoDTO(salvo);
    }
    @Transactional(readOnly = true)
    public List<EventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(EventoDTO::new)
                .toList();
    }
    public EventoDTO finalizarEvento(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        evento.setFinalizado(true);

        // Se a dataFim ainda não foi definida, use a data atual
        if (evento.getDataFim() == null) {
            evento.setDataFim(LocalDate.now());
        }
        return new EventoDTO(eventoRepository.save(evento));
    }
    public void deletarEvento(Long eventoId){
        eventoRepository.findById(eventoId)
                .orElseThrow(()-> new RuntimeException("Evento não encontrado"));

        eventoRepository.deleteById(eventoId);

    }

}

