package com.ifs.controlejogos.services;

import com.ifs.controlejogos.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
}
