package com.abp_backend.mudancas_climaticas.controller;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.model.Sensor;
import com.abp_backend.mudancas_climaticas.model.TipoDado;
import com.abp_backend.mudancas_climaticas.service.SensorService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sensores")
public class SensorController {

   @Autowired
   private SensorService service;

   // Métodos para CRUD de Sensores --------------------------------------------
   // Método para listar todos os sensores
   @GetMapping // GET /api/sensores
   public ResponseEntity<Page<Sensor>> getAllSensores(Pageable pageable) {
      Page<Sensor> sensoresPage = service.getAllSensores(pageable);
      return ResponseEntity.ok(sensoresPage);
   }
   
   // Método para criar sensor
   @PostMapping // POST /api/sensores
   public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
      sensor.defineDescricao();
      Sensor updatedSensor = service.saveSensor(sensor);
      return ResponseEntity.status(HttpStatus.CREATED).body(updatedSensor);
   }

   // Método para buscar sensor por id
   @GetMapping("/{id}") // GET /api/sensores/{id}
   public ResponseEntity<Sensor> getSensorById(@PathVariable Long id) {
      Optional<Sensor> sensor = service.getSensorById(id);
      return sensor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   // Método para atualizar sensor
   @PutMapping("/{id}") // PUT /api/sensores/{id}
   public ResponseEntity<Sensor> updateSensor(@PathVariable Long id, @RequestBody Sensor sensor) {
      Optional<Sensor> updatedSensor = service.updateSensor(id, sensor);
      return updatedSensor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   @DeleteMapping("/{id}") // DELETE /api/sensores/{id}
   public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
      if (service.deleteSensor(id)) return ResponseEntity.ok().build();
      return ResponseEntity.notFound().build();
   }
   
   // Método para resetar o banco de dados - Somente para testes
   @DeleteMapping("/reset") // DELETE /api/sensores/reset
   @Transactional
   public ResponseEntity<Void> resetDatabase() {
      service.resetDatabase();
      return ResponseEntity.ok().build();
   }
   
   // Método para coletar e salvar dados ambientais
   @PostMapping("/{id}/dados") // POST /api/sensores/{id}/dados
   public ResponseEntity<DadosAmbientais> coletarESalvarDados(
      @PathVariable Long id,
      @RequestParam TipoDado tipoDado, // &tipoDado=TipoDado
      @RequestParam double valor) { // &valor=double
      DadosAmbientais dados = service.coletarESalvarDados(id, tipoDado, valor);
      if (dados != null) {
         return ResponseEntity.status(HttpStatus.CREATED).body(dados);
      }
      return ResponseEntity.notFound().build();
   }
}
