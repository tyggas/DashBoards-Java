package com.example.Monitoramento.controller;

import com.example.Monitoramento.Service.MonitoramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/monitoramento")
public class MonitoramentoController {

    @Autowired
    private MonitoramentoService monitoramentoService;

    @PostMapping("/receber")
    public void receberDadosMonitoramento(@RequestBody DadosMonitoramento dadosMonitoramento) {
        LocalDateTime time = LocalDateTime.parse(dadosMonitoramento.getTimestamp(), DateTimeFormatter.ISO_DATE_TIME);
        monitoramentoService.receberDadosMonitoramento(dadosMonitoramento.getSensorId(), dadosMonitoramento.getValor(), time);
    }
}

class DadosMonitoramento {
    private Long sensorId;
    private double valor;
    private String timestamp;

    // Getters e Setters
    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
