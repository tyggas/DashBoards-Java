package com.example.Monitoramento.Repository;

import com.example.Monitoramento.Model.TipoMaquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMaquinaRepository extends JpaRepository<TipoMaquina, Long> {
    // Métodos específicos de consulta podem ser adicionados aqui conforme necessário
}

