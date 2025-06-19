package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.EnumCurso;
import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    //C
    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    //R
    @Transactional(readOnly = true)
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    //U
    public void atualizarCurso(Long id, Curso curso) {
        Optional<Curso> resultado = cursoRepository.findById(id);

        if (resultado.isPresent()) {
            Curso cursoExistente = resultado.get();

            cursoExistente.setNome(curso.getNome());
            cursoExistente.setTipoCurso(curso.getTipoCurso());

            cursoRepository.save(cursoExistente);
        } else {
            throw new RuntimeException("Esse ID não está vinculado com nenhum curso!");
        }
    }

    //D
    public void deletarCurso(Long id) {
        Optional<Curso> resultado = cursoRepository.findById(id);

        if (resultado.isPresent()) {
            cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum curso!");
        }
    }

    @Transactional(readOnly = true)
    public Curso acharPorId(Long id) {
        Optional<Curso> resultado = cursoRepository.findById(id);

        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum curso!");
        }
    }

    @Transactional(readOnly = true)
    public List<Curso> listarCursosIntegrados() {
        List<Curso> cursos = cursoRepository.findAll().stream()
                .filter(curso -> curso.getTipoCurso() == EnumCurso.INTEGRADO) //
                .toList();

        if (cursos.isEmpty()) {
            throw new RuntimeException("Nenhum curso INTEGRADO encontrado");
        }
        return cursos;
    }

    @Transactional(readOnly = true)
    public List<Curso> listarCursosTecnicos() {
        List<Curso> cursos = cursoRepository.findAll().stream()
                .filter(curso -> curso.getTipoCurso() == EnumCurso.TECNICO) //
                .toList();

        if (cursos.isEmpty()) {
            throw new RuntimeException("Nenhum curso TECNICO encontrado");
        }
        return cursos;
    }

    @Transactional(readOnly = true)
    public List<Curso> listarCursosSuperiores() {
        List<Curso> cursos = cursoRepository.findAll().stream()
                .filter(curso -> curso.getTipoCurso() == EnumCurso.SUPERIOR) //
                .toList();

        if (cursos.isEmpty()) {
            throw new RuntimeException("Nenhum curso SUPERIOR encontrado");
        }

        return cursos;
    }
}
