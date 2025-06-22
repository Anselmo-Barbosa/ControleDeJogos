package com.ifs.controlejogos.services;

import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Evento;
import com.ifs.controlejogos.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;


}
