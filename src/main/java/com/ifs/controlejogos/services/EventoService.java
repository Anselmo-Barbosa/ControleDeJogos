package com.ifs.controlejogos.services;

import com.ifs.controlejogos.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventoService {

    private EventoRepository eventoRepository;
}
