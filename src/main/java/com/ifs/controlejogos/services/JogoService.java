package com.ifs.controlejogos.services;

import com.ifs.controlejogos.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogoService {

    private JogoRepository jogoRepository;
}
