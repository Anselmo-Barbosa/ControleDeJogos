package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.dto.UsuarioMinDTO;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.LangUtil;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;


    }


