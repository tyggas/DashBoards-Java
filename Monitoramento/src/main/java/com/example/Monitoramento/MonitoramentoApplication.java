package com.example.Monitoramento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.Monitoramento")
@EnableJpaRepositories(basePackages = "com.example.Monitoramento")
public class MonitoramentoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonitoramentoApplication.class, args);
	}
}
