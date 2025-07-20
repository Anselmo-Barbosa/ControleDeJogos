package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.CursoDTO;
import com.ifs.controlejogos.entities.*;
import com.ifs.controlejogos.form.CursoFORM;
import com.ifs.controlejogos.repository.CampusRepository;
import com.ifs.controlejogos.repository.CursoRepository;
import com.ifs.controlejogos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CampusRepository campusRepository;

    //C
    public CursoDTO criarCurso(CursoFORM cursoFORM) {
        Usuario coordenador = usuarioRepository.findById(cursoFORM.getCoordenadorId())
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));

        Campus campus = campusRepository.findById(cursoFORM.getCampusId())
                .orElseThrow(() -> new RuntimeException("Campus não encontrado"));

        if (coordenador.getTipoUsuario() != EnumUsuario.COORDENADOR) {
            throw new RuntimeException("Usuário não é um coordenador válido");
        }

        Curso curso = new Curso();
        curso.setNome(cursoFORM.getNome());
        curso.setTipoCurso(cursoFORM.getTipoCurso());
        curso.setCoordenador(coordenador);
        curso.setCampus(campus);

        Curso cursoSalvo = cursoRepository.save(curso);

        //atualizando os outros lado da relação
        coordenador.setCurso(cursoSalvo);
        usuarioRepository.save(coordenador);

        campus.getCursos().add(curso);
        campusRepository.save(campus);

        return new CursoDTO(cursoSalvo);
    }

    //R
    @Transactional(readOnly = true)
    public List<CursoDTO> listarCursos() {

        List<Curso> listacursos = cursoRepository.findAll();
        return listacursos.stream()
                .map(e -> new CursoDTO(e))
                .toList();
    }

    //U
    public void atualizarCurso(Long id, Curso curso) {
        Curso encontrado = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum curso encontrado"));

        encontrado.setNome(curso.getNome());
        encontrado.setTipoCurso(curso.getTipoCurso());

        cursoRepository.save(encontrado);

    }

    //D
    public void deletarCurso(Long id) {
        Curso encontrado = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum curso encontrado"));

        cursoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CursoDTO acharPorId(Long id) {
        Curso encontrado = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum curso encontrado"));

        return new CursoDTO(encontrado);
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> listarPorTipo(EnumCurso enumCurso) {
        List<Curso> cursos = cursoRepository.findByTipoCurso(enumCurso);

        if (cursos.isEmpty()) {
            throw new RuntimeException("Não existe nenhum curso " + enumCurso + " Cadastrado");
        }
        return cursos.stream()
                .map(curso -> new CursoDTO(curso))
                .toList();
    }
}
