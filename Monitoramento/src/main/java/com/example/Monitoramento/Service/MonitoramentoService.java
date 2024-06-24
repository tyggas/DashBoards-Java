package com.example.Monitoramento.Service;

import com.example.Monitoramento.Model.Alerta;
import com.example.Monitoramento.Model.Monitoramento;
import com.example.Monitoramento.Model.Sensor;
import com.example.Monitoramento.Repository.AlertaRepository;
import com.example.Monitoramento.Repository.MonitoramentoRepository;
import com.example.Monitoramento.Repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
public class MonitoramentoService {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    private Random random = new Random();

    @PostConstruct
    public void init() {
        new Thread(this::gerarMonitoramentos).start();
    }

    public void gerarMonitoramentos() {
        while (true) {
            List<Sensor> sensores = sensorRepository.findAll();
            Sensor sensor = sensores.get(random.nextInt(sensores.size()));
            LocalDateTime timestamp = LocalDateTime.now();
            double valor = gerarValor(sensor.getTipo());

            Monitoramento monitoramento = new Monitoramento();
            monitoramento.setSensor(sensor);
            monitoramento.setTimestamp(timestamp);
            monitoramento.setValor(valor);

            monitoramentoRepository.save(monitoramento);

            if (random.nextDouble() < 0.1) {
                Alerta alerta = new Alerta();
                alerta.setSensor(sensor);
                alerta.setHoraAlerta(timestamp);
                alerta.setDescricao(gerarDescricao(sensor.getTipo()));
                alerta.setStatus(random.nextBoolean() ? "Pendente" : "Resolvido");

                alertaRepository.save(alerta);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private double gerarValor(String tipo) {
        switch (tipo) {
            case "Temperatura":
                return round(random.nextDouble() * 20 + 15, 2);
            case "Umidade":
                return round(random.nextDouble() * 40 + 30, 2);
            case "Pressão":
                return round(random.nextDouble() * 1000 + 1000, 2);
            case "Vibração":
                return round(random.nextDouble() * 10, 2);
            case "pH":
                return round(random.nextDouble() * 14, 2);
            case "Precisão de Corte":
                return round(random.nextDouble(), 2);
            default:
                return 0;
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

    private double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
