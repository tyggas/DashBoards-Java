package com.example.Monitoramento.Repository;

import com.example.Monitoramento.Model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
