package com.ifs.controlejogos.services;

import com.ifs.controlejogos.repository.EquipeRepository;
import com.ifs.controlejogos.repository.EsporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EsporteService {

    @Autowired
    private EsporteRepository esporteRepository;
}
