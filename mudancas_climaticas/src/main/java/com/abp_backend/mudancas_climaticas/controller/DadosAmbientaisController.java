package com.abp_backend.mudancas_climaticas.controller;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.model.Sensor;
import com.abp_backend.mudancas_climaticas.service.DadosAmbientaisService;
import com.abp_backend.mudancas_climaticas.service.SensorService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/dados-ambientais")
public class DadosAmbientaisController {

   @Autowired
   private DadosAmbientaisService service;
   
   @Autowired
   private SensorService sensorService;

   // Métodos para CRUD de Dados Ambientais ------------------------------------
   // Método para listar todos os dados ambientais
   @GetMapping // GET /api/dados-ambientais
   public ResponseEntity<Page<DadosAmbientais>> getAllDadosAmbientais(Pageable pageable) {
      Page<DadosAmbientais> dadosAmbientaisPage = service.getAllDadosAmbientais(pageable);
      return ResponseEntity.ok(dadosAmbientaisPage);
   }
   
   // Método para criar dado ambiental
   @PostMapping // POST /api/dados-ambientais
   public ResponseEntity<DadosAmbientais> createDadosAmbientais(@RequestBody DadosAmbientais dadosAmbientais) {
      if (dadosAmbientais == null || dadosAmbientais.getSensor() == null) return ResponseEntity.badRequest().build();
       
      Long sensorId = dadosAmbientais.getSensor().getId();
      Optional<Sensor> sensorOptional = sensorService.getSensorById(sensorId);
       
      if (sensorOptional.isEmpty()) return ResponseEntity.badRequest().build();
       
      Sensor sensor = sensorOptional.get();
      dadosAmbientais.setSensor(sensor);
       
      DadosAmbientais createdDadosAmbientais = service.saveDadosAmbientais(dadosAmbientais);
      return ResponseEntity.ok(createdDadosAmbientais);
   }
   
   // Método para buscar dados ambientais por id
   @GetMapping("/{id}") // GET /api/dados-ambientais/{id}
   public ResponseEntity<DadosAmbientais> getDadosAmbientaisById(@PathVariable Long id) {
      return service.getDadosAmbientaisById(id)
              .map(dadosAmbientais -> ResponseEntity.ok().body(dadosAmbientais))
              .orElse(ResponseEntity.notFound().build());
   }

   // Método para atualizar dados ambientais
   @PutMapping("/{id}") // PUT /api/dados-ambientais/{id}
   @Transactional
   public ResponseEntity<DadosAmbientais> updateDadosAmbientais(@PathVariable Long id, @RequestBody DadosAmbientais dadosAmbientais) {
      Optional<DadosAmbientais> updatedDadosAmbientais = service.updateDadosAmbientais(id, dadosAmbientais);
      return updatedDadosAmbientais.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   // Método para deletar dados ambientais
   @DeleteMapping("/{id}") // DELETE /api/dados-ambientais/{id}
   @Transactional
   public ResponseEntity<Void> deleteDadosAmbientais(@PathVariable Long id) {
      if (service.deleteDadosAmbientais(id)) {
         return ResponseEntity.ok().build();
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Método para resetar o banco de dados - Somente para testes
   @DeleteMapping("/reset") // DELETE /api/dados-ambientais/reset
   @Transactional
   public ResponseEntity<Void> resetDatabase() {
      service.resetDatabase();
      return ResponseEntity.ok().build();
   }
}
