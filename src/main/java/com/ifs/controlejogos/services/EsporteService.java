package com.ifs.controlejogos.services;

import com.ifs.controlejogos.dto.EsporteDTO;
import com.ifs.controlejogos.entities.Esporte;
import com.ifs.controlejogos.entities.Evento;
import com.ifs.controlejogos.form.EsporteFORM;
import com.ifs.controlejogos.repository.EsporteRepository;
import com.ifs.controlejogos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EsporteService {

    @Autowired
    private EsporteRepository esporteRepository;
    @Autowired
    private EventoRepository eventoRepository;

    //C
    public EsporteDTO criarEsporte(EsporteFORM esporte) {

        if (esporteRepository.existsByNome(esporte.getNome())) {
            throw new RuntimeException("Ja existe um esporte com esse nome cadastrado");
        }
        Evento evento = eventoRepository.findById(esporte.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Esporte esporte1 = esporte.toEsporte(evento);
        esporteRepository.save(esporte1);

        //Salva no outro lado da relação (Evento)
        evento.getEsportes().add(esporte1);
        eventoRepository.save(evento);

        return new EsporteDTO(esporte1);
    }

    //R
    @Transactional(readOnly = true)
    public List<EsporteDTO> listarEsportes() {
        return esporteRepository.findAll().stream()
                .map(esporte -> new EsporteDTO(esporte))
                .toList();
    }

    //U
    public void atualizarEsporte(Long id, Esporte esporte) {
        Esporte resultado = esporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));


        resultado.setNome(esporte.getNome());
        esporteRepository.save(resultado);
    }

    //D
    public void deletarEsporte(Long id) {
        esporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        esporteRepository.deleteById(id);
    }

    public EsporteDTO acharPorId(Long id) {
        Esporte encontrado = esporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esporte não encontrado"));

        return new EsporteDTO(encontrado);
    }
}
