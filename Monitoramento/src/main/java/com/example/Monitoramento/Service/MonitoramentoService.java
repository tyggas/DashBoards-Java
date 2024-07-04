package com.example.Monitoramento.Service;

import com.example.Monitoramento.Model.Alerta;
import com.example.Monitoramento.Model.Monitoramento;
import com.example.Monitoramento.Model.Sensor;
import com.example.Monitoramento.Repository.AlertaRepository;
import com.example.Monitoramento.Repository.MonitoramentoRepository;
import com.example.Monitoramento.Repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class MonitoramentoService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    public void receberDadosMonitoramento(Long sensorId, double valor, LocalDateTime timestamp) {
        Optional<Sensor> sensorOpt = sensorRepository.findById(sensorId);
        if (sensorOpt.isPresent()) {
            Sensor sensor = sensorOpt.get();

            Monitoramento monitoramento = new Monitoramento();
            monitoramento.setSensor(sensor);
            monitoramento.setTimestamp(timestamp);
            monitoramento.setValor(valor);

            monitoramentoRepository.save(monitoramento);

            // Verifique se algum alerta deve ser gerado com base nos valores recebidos
            if (valorExcedeLimite(sensor.getTipo(), valor)) {
                Alerta alerta = new Alerta();
                alerta.setSensor(sensor);
                alerta.setHoraAlerta(timestamp);
                alerta.setDescricao(gerarDescricao(sensor.getTipo()));
                alerta.setStatus("Pendente");

                alertaRepository.save(alerta);
            }
        } else {
            // Tratar caso onde o sensorId não exista
            System.out.println("Sensor com ID " + sensorId + " não encontrado.");
        }
    }

    private boolean valorExcedeLimite(String tipo, double valor) {
        switch (tipo) {
            case "Temperatura":
                return valor > 30; // Exemplo de limite
            case "Umidade":
                return valor < 30 || valor > 70; // Exemplo de limite
            case "Pressão":
                return valor < 1000 || valor > 1100; // Exemplo de limite
            case "Vibração":
                return valor > 5; // Exemplo de limite
            case "pH":
                return valor < 6 || valor > 8; // Exemplo de limite
            case "Precisão de Corte":
                return valor < 0.5; // Exemplo de limite
            default:
                return false;
        }
    }

    private String gerarDescricao(String tipo) {
        switch (tipo) {
            case "Temperatura":
                return "Temperatura acima do limite";
            case "Umidade":
                return "Umidade fora do intervalo";
            case "Pressão":
                return "Pressão acima do limite";
            case "Vibração":
                return "Vibração excessiva";
            case "pH":
                return "pH fora do intervalo";
            case "Precisão de Corte":
                return "Precisão de corte fora do padrão";
            default:
                return "Alerta desconhecido";
        }
    }
}
