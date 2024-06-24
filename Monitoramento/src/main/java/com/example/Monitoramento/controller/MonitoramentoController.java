package com.example.Monitoramento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitoramentoController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
