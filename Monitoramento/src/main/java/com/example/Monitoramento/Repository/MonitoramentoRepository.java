package com.example.Monitoramento.Repository;

import com.example.Monitoramento.Model.Monitoramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {
}
