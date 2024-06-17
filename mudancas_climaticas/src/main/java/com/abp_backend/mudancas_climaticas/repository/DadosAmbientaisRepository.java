package com.abp_backend.mudancas_climaticas.repository;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosAmbientaisRepository extends JpaRepository<DadosAmbientais, Long> {
   List<DadosAmbientais> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);
   List<DadosAmbientais> findBySensorIdAndDataHoraBetween(Long sensorId, LocalDateTime start, LocalDateTime end);
   List<DadosAmbientais> findByLocalizacaoAndDataHoraBetween(String localizacao, LocalDateTime start, LocalDateTime end);
}

