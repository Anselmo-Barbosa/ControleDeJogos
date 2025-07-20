package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.EquipeDTO;
import com.ifs.controlejogos.entities.*;
import com.ifs.controlejogos.repository.CursoRepository;
import com.ifs.controlejogos.repository.EquipeRepository;
import com.ifs.controlejogos.repository.EsporteRepository;
import com.ifs.controlejogos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private EsporteRepository esporteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public EquipeDTO criarEquipe(Long tecnicoId, Equipe equipe) {

        Usuario tecnico = usuarioRepository.findById(tecnicoId)
                .orElseThrow(() -> new RuntimeException("Tecnico não encontrado"));

        if (tecnico.getTipoUsuario() != EnumUsuario.TECNICO){
            throw new RuntimeException("Só é possível atribuir um usuario TECNICO á equipe");
        }

        Esporte esporte = esporteRepository.findById(equipe.getEsporteId())
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        int numJogadores = equipe.getUsuariosId().size();
        if (numJogadores < esporte.getMin_jogadores() || numJogadores > esporte.getMax_jogadores()) {
            throw new RuntimeException("Não é possível cadastrar a equipe: É necessario o mínimo de " + esporte.getMin_jogadores()
                    + " e no máximo " + esporte.getMax_jogadores() + " jogadores em " + esporte.getNome());
        }

        Curso curso = cursoRepository.findById(equipe.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        List<Usuario> atletas = new ArrayList<>();

        for (Long id : equipe.getUsuariosId()) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Um ou mais usuarios não encontrados"));

            if(usuario.getTipoUsuario() == EnumUsuario.ATLETA) {
                atletas.add(usuario);
            }
            else {
                throw new RuntimeException("Apenas ATLETAS podem competir");
            }
        }
        equipe.setCurso(curso);
        equipe.setEsporte(esporte);
        equipe.setTecnico(tecnico);
        equipe.setUsuarios(atletas);


        boolean equipeExisteEsporteCurso = equipeRepository.existsByEsporteAndCurso(equipe.getEsporte(), equipe.getCurso());

        if (equipeExisteEsporteCurso) {
            throw new RuntimeException("Não é possível cadastrar a equipe: Já existe uma equipe para esse curso nesse esporte");
        }

        //atualizando o outro lado da relação(Usuarios)
        Equipe equipeSalva = equipeRepository.save(equipe);

        // Atualizando os atletas
        for (Usuario atleta : atletas) {
            atleta.getEquipes().add(equipeSalva);
        }
        // Atualizando o técnico

        tecnico.getEquipes().add(equipeSalva);

       // Salvando os usuários atualizados
        usuarioRepository.saveAll(atletas);
        usuarioRepository.save(tecnico);

        esporte.getEquipes().add(equipeSalva);
        esporteRepository.save(esporte);

        return new EquipeDTO(equipeSalva);
    }

    //R
    @Transactional(readOnly = true)
    public List<EquipeDTO> listarEquipes() {
        List<EquipeDTO> listaequipes = equipeRepository.findAll()
                .stream()
                .map(equipes -> new EquipeDTO(equipes))
                .toList();

        return listaequipes;
    }

    //U
    public void atualizarEquipe(Long id, Equipe equipe) {
        Optional<Equipe> resultado = equipeRepository.findById(id);

        if (resultado.isPresent()) {
            Equipe equipeExistente = resultado.get();

            equipeExistente.setNome(equipe.getNome());

            equipeRepository.save(equipeExistente);
        } else {
            throw new RuntimeException("Esse ID não está vinculado com nenhuma equipe!");
        }
    }

    //D
    public void deletarEquipe(Long id) {
        Optional<Equipe> resultado = equipeRepository.findById(id);

        if (resultado.isPresent()) {
            equipeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhuma equipe!");
        }
    }

    @Transactional(readOnly = true)
    public Equipe acharPorId(Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        if (equipe.isPresent()) {
            return equipe.get();
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhuma equipe");
        }
    }
}
