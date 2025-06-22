package com.ifs.controlejogos.services;

import com.ifs.controlejogos.entities.Campus;
import com.ifs.controlejogos.entities.Evento;
import com.ifs.controlejogos.entities.Jogo;
import com.ifs.controlejogos.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.LangUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    //C
    public Jogo criarJogo(Jogo jogo) {
        boolean jogoExisteHoraData = jogoRepository.existsByDataAndHora(jogo.getData(), jogo.getHoraInicio(), jogo.getHoraFim());

        if (!jogoExisteHoraData){
            return jogoRepository.save(jogo);
        }
        else{
            throw new RuntimeException("Não é possível marcar jogo: Há um jogo marcado neste horario");
        }
    }
    //R
    @Transactional(readOnly = true)
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    //U
    //Apenas o arbitro pode modificar jogos
    public void atualizarJogo(Long id, Jogo jogo) {
        Optional<Jogo> resultado = jogoRepository.findById(id);

        if (resultado.isPresent()) {
            Jogo jogoExistente = resultado.get();

            jogoExistente.setData(jogo.getData());
            jogoExistente.setHoraInicio(jogo.getHoraInicio());
            jogoExistente.setHoraFim(jogo.getHoraFim());
            jogoExistente.setPlacarEquipeA(jogo.getPlacarEquipeA());
            jogoExistente.setPlacarEquipeB(jogo.getPlacarEquipeB());

            jogoRepository.save(jogoExistente);
        } else {
            throw new RuntimeException("Esse ID não está vinculado com nenhum jogo!");
        }
    }

    //D
    public void deletarJogo(Long id) {
        Optional<Jogo> resultado = jogoRepository.findById(id);

        if (resultado.isPresent()) {
            jogoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Este ID não está vinculado com nenhum Jogo!");
        }
    }

    public Jogo acharPorId(Long id){
     Optional<Jogo> resultado = jogoRepository.findById(id);

     if(resultado.isPresent()){
         return resultado.get();
     }
     else{
         throw new RuntimeException("Este ID não está vinculado com nenhum jogo!");
     }
    }

}
