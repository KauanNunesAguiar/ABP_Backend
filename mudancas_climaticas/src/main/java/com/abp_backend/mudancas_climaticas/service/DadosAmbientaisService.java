package com.abp_backend.mudancas_climaticas.service;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.repository.DadosAmbientaisRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DadosAmbientaisService {

   @Autowired
   private DadosAmbientaisRepository repository;
   
   @PersistenceContext
   private EntityManager entityManager;
   
   public Page<DadosAmbientais> getAllDadosAmbientais(Pageable pageable) {
      return repository.findAll(pageable);
   }
   
   public Optional<DadosAmbientais> getDadosAmbientaisById(Long id) {
      return repository.findById(id);
   }
   
   public Optional<DadosAmbientais> updateDadosAmbientais(Long id, DadosAmbientais dados) {
      Optional<DadosAmbientais> existingDados = repository.findById(id);
      if (existingDados.isPresent()) {
          dados.setId(id);
          return Optional.of(repository.save(dados));
      }
      return Optional.empty();
   }
   
   public List<DadosAmbientais> buscarPorIntervaloDeTempo(LocalDate inicio, LocalDate fim) {
      LocalDateTime startDateTime = inicio.atStartOfDay();
      LocalDateTime endDateTime = fim.plusDays(1).atStartOfDay().minusSeconds(1); // Inclui o fim do dia
      return repository.findByDataHoraBetween(startDateTime, endDateTime);
   }
   
   public List<DadosAmbientais> buscarPorSensorEIntervaloDeTempo(Long sensorId, LocalDate inicio, LocalDate fim) {
      LocalDateTime startDateTime = inicio.atStartOfDay();
      LocalDateTime endDateTime = fim.plusDays(1).atStartOfDay().minusSeconds(1); // Inclui o fim do dia
      return repository.findBySensorIdAndDataHoraBetween(sensorId, startDateTime, endDateTime);
   }

   public List<DadosAmbientais> buscarPorLocalizacaoEIntervaloDeTempo(String localizacao, LocalDate inicio, LocalDate fim) {
      LocalDateTime startDateTime = inicio.atStartOfDay();
      LocalDateTime endDateTime = fim.plusDays(1).atStartOfDay().minusSeconds(1); // Inclui o fim do dia
      return repository.findByLocalizacaoAndDataHoraBetween(localizacao, startDateTime, endDateTime);
   }
   
   public boolean deleteDadosAmbientais(Long id) {
      if (repository.existsById(id)) {
         repository.deleteById(id);
         return true;
      }
      return false;
   }
   
   public DadosAmbientais saveDadosAmbientais(DadosAmbientais dados) {
      return repository.save(dados);
   }
   
   public void resetDatabase() {
      repository.deleteAll();
      entityManager.createNativeQuery("ALTER TABLE dados_ambientais ALTER COLUMN id RESTART WITH 1").executeUpdate();
   }
}
