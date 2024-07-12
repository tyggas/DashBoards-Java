package com.example.Monitoramento.Service;

import com.example.Monitoramento.Model.TipoMaquina;
import com.example.Monitoramento.Repository.TipoMaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMaquinaService {

    @Autowired
    private TipoMaquinaRepository tipoMaquinaRepository;

    public List<TipoMaquina> listarTiposMaquina() {
        return tipoMaquinaRepository.findAll();
    }
}
