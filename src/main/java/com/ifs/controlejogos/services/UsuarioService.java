package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.UsuarioDTO;
import com.ifs.controlejogos.entities.EnumUsuario;
import com.ifs.controlejogos.entities.Usuario;
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

    //C
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //R
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> listausuarios = usuarioRepository.findAll();
        return listausuarios.stream().map(e -> new UsuarioDTO(e)).toList();
    }
    //U
    public void atualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> resultado = usuarioRepository.findById(id);

        if (resultado.isPresent()) {
            Usuario usuarioExistente = resultado.get();

            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setTelefone(usuario.getTelefone());

            usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Esse ID não está vinculado com nenhum usuario!");
        }
    }
    //D
    public void deletarUsuario(Long id) {
        Optional<Usuario> resultado = usuarioRepository.findById(id);

        if (resultado.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum usuario!");
        }
    }

    @Transactional(readOnly = true)
    public UsuarioDTO acharPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return new UsuarioDTO(usuario.get());
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum usuario!");
        }
    }

    @Transactional(readOnly = true)
    public UsuarioDTO acharPorMatricula(String matricula) {
        Usuario usuario = usuarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Esta matricula não está vinculado com nenhum usuario!"));

        return new UsuarioDTO(usuario);
    }

    //Metodo especial exclusivo para pre-Cadastrar coodernadores
    public Usuario criarCoordernador(String nome, String telefone) {
        Usuario coodenador = new Usuario();

        coodenador.setNome(nome);
        coodenador.setTelefone(telefone);
        coodenador.setTipoUsuario(EnumUsuario.COORDENADOR);

        //Gerando um login (falsa matricula pro atributo)
        String prefixo = "COORD";
        int sufixo = (int) (Math.random() * 10000);
        String loguinGerado = prefixo + sufixo;

        coodenador.setMatricula(loguinGerado);

        //Gerando uma por meio de uma UUID, usado na maioria dos sites (gera uma senha de 0 a 10 impossivel de se repetir)
        String senhaGerada = UUID.randomUUID().toString().substring(0, 10);
        coodenador.setSenha(senhaGerada);

        //Simular o envio do email pelo console
        System.out.println("Coodenador pré cadastrado com sucesso!");
        System.out.println("id: " + coodenador.getId());
        System.out.println("Nome: " + coodenador.getNome());
        System.out.println("Telefone: " + coodenador.getTelefone());
        System.out.println("Login: " + coodenador.getMatricula());
        System.out.println("Senha: " + coodenador.getSenha());

        return usuarioRepository.save(coodenador);

    }

    @Transactional(readOnly = true)
    //Usando stream pra manejar a Lista (Fitrar, alterar. e jogar em outra lista novamente)
    public List<UsuarioDTO> listarCoodenadores() {
        List<UsuarioDTO> coordenadores = usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getTipoUsuario() == EnumUsuario.COORDENADOR) //
                .map(UsuarioDTO::new)
                .toList();

        if (coordenadores.isEmpty()) {
            throw new RuntimeException("Nenhum coordenador encontrado!");
        }

        return coordenadores;
    }

    public void tornarTecnico(Long id) {
        Optional<Usuario> tecnico = usuarioRepository.findById(id);

        if (tecnico.isPresent()) {
            Usuario usuarioExistente = tecnico.get();

            usuarioExistente.setTipoUsuario(EnumUsuario.TECNICO);

            usuarioRepository.save(usuarioExistente);
            System.out.println("Função TECNICO atribuida á " + usuarioExistente.getNome());
        }
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarTecnicos() {
        List<UsuarioDTO> tecnicos = usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getTipoUsuario() == EnumUsuario.TECNICO) //
                .map(UsuarioDTO::new)
                .toList();

        if (tecnicos.isEmpty()) {
            throw new RuntimeException("Nenhum coordenador encontrado!");
        }

        return tecnicos;
    }
}


