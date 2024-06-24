package com.abp_backend.mudancas_climaticas.service;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.model.Sensor;
import com.abp_backend.mudancas_climaticas.model.TipoSensor;
import com.abp_backend.mudancas_climaticas.repository.SensorRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

   @Autowired
   private SensorRepository repository;
   
   @Autowired
   private DadosAmbientaisService dadosAmbientaisService;
   
   @PersistenceContext
   private EntityManager entityManager;

   public Page<Sensor> getAllSensores(Pageable pageable) {
      return repository.findAll(pageable);
   }

   public Sensor saveSensor(Sensor sensor) {
      return repository.save(sensor);
   }

   public Optional<Sensor> getSensorById(Long id) {
      return repository.findById(id);
   }
   
   public List<Sensor> getSensorsByUsuario(Long usuarioId) {
      return repository.findByUsuarioId(usuarioId);
   }
   
   public Optional<Sensor> updateSensor(Long id, Sensor sensor) {
      Optional<Sensor> existingSensor = repository.findById(id);
      if (existingSensor.isPresent()) {
         sensor.setId(id);
         return Optional.of(repository.save(sensor));
      }
      return Optional.empty();
   }
   
   public DadosAmbientais colectData(Long sensorId, TipoSensor tipoDado, double valor) {
      Optional<Sensor> sensorOptional = repository.findById(sensorId);
      if (sensorOptional.isPresent()) {
         Sensor sensor = sensorOptional.get();
         DadosAmbientais dados = sensor.coletarDados(tipoDado, valor);
         if (dados != null) {
            return dadosAmbientaisService.saveDadosAmbientais(dados);
         }
      }
      return null;
  }
   
   public boolean deleteSensor(Long id) {
      if (repository.existsById(id)) {
         repository.deleteById(id);
         return true;
      }
      return false;
   }
   
   public void resetDatabase() {
      repository.deleteAll();
      entityManager.createNativeQuery("ALTER TABLE sensor_ambiente ALTER COLUMN id RESTART WITH 1").executeUpdate();
   }
}
