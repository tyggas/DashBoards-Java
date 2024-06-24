package com.example.Monitoramento.Repository;

import com.example.Monitoramento.Model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
