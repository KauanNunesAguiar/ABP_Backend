package com.abp_backend.mudancas_climaticas.repository;

import com.abp_backend.mudancas_climaticas.model.Sensor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
   List<Sensor> findByUsuarioId(Long usuarioId);
}
