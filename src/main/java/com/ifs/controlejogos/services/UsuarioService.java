package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.CampusDTO;
import com.ifs.controlejogos.dto.CoordenadorDTO;
import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.Curso;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
import com.ifs.controlejogos.form.CoordenadorFORM;
import com.ifs.controlejogos.form.UsuarioFORM;
import com.ifs.controlejogos.repository.CursoRepository;
import com.ifs.controlejogos.repository.UsuarioRepository;
import com.ifs.controlejogos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    //C
    public UsuarioDTO criarUsuario(UsuarioFORM usuarioForm) {
        if (usuarioRepository.existsByMatricula(usuarioForm.getMatricula())){
            throw new RuntimeException("Ja existe um usuario cadastrado com essa matricula!");
        }

        Curso curso = cursoRepository.findById(usuarioForm.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso com ID " + usuarioForm.getCursoId() + " não encontrado!"));

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioForm.getNome());
        usuario.setTelefone(usuarioForm.getTelefone());
        usuario.setMatricula(usuarioForm.getMatricula());
        usuario.setSenha(usuarioForm.getSenha());
        usuario.setCurso(curso);
        usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }
    //R
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> listausuarios = usuarioRepository.findAll();

        return listausuarios.stream().map(e -> new UsuarioDTO(e)).toList();
    }
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuariosPorTipo(EnumUsuario enumUsuario) {
        List<Usuario> listausuarios = usuarioRepository.findByTipoUsuario(enumUsuario);

        return listausuarios.stream().map(e -> new UsuarioDTO(e)).toList();


    }
    //U
    public void atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuarioEncontrado.setNome(usuario.getNome());
        usuarioEncontrado.setTelefone(usuario.getTelefone());
        usuarioEncontrado.setSenha(usuario.getSenha());

        usuarioRepository.save(usuarioEncontrado);
    }
    //D
    public void deletarUsuario(Long id) {
        usuarioRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO acharPorId(Long id) {
      Usuario usuarioEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        return new UsuarioDTO(usuarioEncontrado);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO acharPorMatricula(String matricula) {
        Usuario usuarioEncontrado = usuarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Esta matricula não está vinculado com nenhum usuario!"));

        return new UsuarioDTO(usuarioEncontrado);
    }

    public CoordenadorDTO criarCoordernador(CoordenadorFORM coordenadorForm) {
        if (usuarioRepository.existsByMatricula(coordenadorForm.getMatricula())){
            throw new RuntimeException("Ja existe um COORDENADOR cadastrado com essa matricula!");
        }

        Usuario coordenador = new Usuario();

        coordenador.setNome(coordenadorForm.getNome());
        coordenador.setTelefone(coordenadorForm.getTelefone());
        coordenador.setMatricula(coordenadorForm.getMatricula());
        coordenador.setTipoUsuario(EnumUsuario.COORDENADOR);

        //Gerando uma senha por meio de uma UUID (gera uma senha de 0 a 8 impossivel de se repetir)
        String senhaGerada = UUID.randomUUID().toString().substring(0, 8);
        coordenador.setSenha(senhaGerada);


        System.out.println("Coodenador pré cadastrado com sucesso! Informações de login enviadas por email");
        System.out.println("id: " + coordenador.getId());
        System.out.println("Nome: " + coordenador.getNome());
        System.out.println("Telefone: " + coordenador.getTelefone());
        System.out.println("Login: " + coordenador.getMatricula());
        System.out.println("Senha: " + coordenador.getSenha());
        usuarioRepository.save(coordenador);

        return new CoordenadorDTO(coordenador);

    }

    public void tornarTecnico(Long id) {
        Usuario tecnicoEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esta matricula não está vinculado com nenhum USUARIO!"));

        //Não pode atribuir a funcão tecnico a um COORDENADOR
        if(tecnicoEncontrado.getTipoUsuario() != EnumUsuario.ATLETA) {
            throw new RuntimeException("Só é possível atribuir a função TECNICO á um ATLETA");
        }

        if(!tecnicoEncontrado.getEquipes().isEmpty()){
            throw new RuntimeException("Operação invalida: Não é possível atribuir a função TECNICO a um JOGADOR na equipe");
        }

            tecnicoEncontrado.setTipoUsuario(EnumUsuario.TECNICO);
            usuarioRepository.save(tecnicoEncontrado);
            System.out.println("Função TECNICO atribuida á " + tecnicoEncontrado.getNome());
    }
}


