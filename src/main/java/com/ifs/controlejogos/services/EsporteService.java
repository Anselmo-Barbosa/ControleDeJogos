package com.ifs.controlejogos.services;

import com.ifs.controlejogos.repository.EquipeRepository;
import com.ifs.controlejogos.repository.EsporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EsporteService {

    private EsporteRepository esporteRepository;
}
