package com.example.Monitoramento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/oee")
public class MonitoramentoController {

    private final Random random = new Random();

    @GetMapping("/calculate")
    public List<Map<String, Object>> calculateOEE() {
        List<Map<String, Object>> machinesOEE = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            // Gerar dados aleatórios para cada máquina
            double horasPeriodo = 24.0;
            double horasJustificadas = random.nextDouble() * 4;
            double horasProduzindo = 20.0;

            double velocidadeReal = 100.0 + random.nextDouble() * 20;
            double velocidadePrevista = 120.0;
            double tempoProduzindoPeriodo = 18.0 + random.nextDouble() * 6;
            double tempoTotalProducao = 24.0;

            double pecasBoas = 950.0 + random.nextDouble() * 50;
            double totalPecas = 1000.0;

            // Cálculos
            double disponibilidade = calcularDisponibilidade(horasPeriodo, horasJustificadas, horasProduzindo);
            double performance = calcularPerformance(velocidadeReal, velocidadePrevista, tempoProduzindoPeriodo, tempoTotalProducao);
            double qualidade = calcularQualidade(pecasBoas, totalPecas);
            double oee = calcularOEE(disponibilidade, performance, qualidade);

            // Mapear os resultados
            Map<String, Object> response = new HashMap<>();
            response.put("machineId", i + 1);
            response.put("disponibilidade", disponibilidade * 100);
            response.put("performance", performance * 100);
            response.put("qualidade", qualidade * 100);
            response.put("oee", oee * 100);

            machinesOEE.add(response);
        }

        return machinesOEE;
    }

    private double calcularDisponibilidade(double horasPeriodo, double horasJustificadas, double horasProduzindo) {
        return (horasPeriodo - horasJustificadas) / horasProduzindo;
    }

    private double calcularPerformance(double velocidadeReal, double velocidadePrevista, double tempoProduzindoPeriodo, double tempoTotalProducao) {
        double percentualTempoProduzindo = tempoProduzindoPeriodo / tempoTotalProducao;
        return (velocidadeReal / velocidadePrevista) * percentualTempoProduzindo;
    }

    private double calcularQualidade(double pecasBoas, double totalPecas) {
        return pecasBoas / totalPecas;
    }

    private double calcularOEE(double disponibilidade, double performance, double qualidade) {
        return disponibilidade * performance * qualidade;
    }
}
