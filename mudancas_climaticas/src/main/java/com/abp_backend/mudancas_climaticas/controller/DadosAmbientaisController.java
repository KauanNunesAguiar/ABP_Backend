package com.abp_backend.mudancas_climaticas.controller;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.service.DadosAmbientaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/dados-ambientais")
public class DadosAmbientaisController {

   @Autowired
   private DadosAmbientaisService service;

   // Métodos para CRUD de Dados Ambientais ------------------------------------
   // Método para listar todos os dados ambientais
   @GetMapping // GET /api/dados-ambientais
   public ResponseEntity<Page<DadosAmbientais>> getAllDadosAmbientais(Pageable pageable) {
      Page<DadosAmbientais> dadosAmbientaisPage = service.getAllDadosAmbientais(pageable);
      return ResponseEntity.ok(dadosAmbientaisPage);
   }

   // Método para buscar dados ambientais por localização e/ou intervalo de tempo
   @GetMapping("/search-by-location") // GET /api/dados-ambientais/search-by-location
   public ResponseEntity<List<DadosAmbientais>> searchDadosAmbientaisByLocation(
      @RequestParam(required = true) String localizacao, // &localizacao=String
      @RequestParam(required = false) LocalDate inicio, // &inicio=yyyy-MM-dd
      @RequestParam(required = false) LocalDate fim, // &fim=yyyy-MM-dd
      Pageable pageable) {

      LocalDate dataInicioDefault = LocalDate.of(2000, 1, 1);

      List<DadosAmbientais> result;

      result = service.buscarPorLocalizacaoEIntervaloDeTempo(localizacao, (inicio == null) ? dataInicioDefault : inicio, (fim == null) ? LocalDate.now() : fim);
      return ResponseEntity.ok(result);
   }

   // Método para buscar dados ambientais por sensor e/ou intervalo de tempo
   @GetMapping("/search-by-sensor") // GET /api/dados-ambientais/search-by-sensor
   public ResponseEntity<List<DadosAmbientais>> searchDadosAmbientaisBySensor(
      @RequestParam(required = true) Long sensorId, // &sensorId=Long
      @RequestParam(required = false) LocalDate inicio, // &inicio=yyyy-MM-dd
      @RequestParam(required = false) LocalDate fim, // &fim=yyyy-MM-dd
      Pageable pageable) {

      LocalDate dataInicioDefault = LocalDate.of(2000, 1, 1);

      List<DadosAmbientais> result;

      result = service.buscarPorSensorEIntervaloDeTempo(sensorId, (inicio == null) ? dataInicioDefault : inicio, (fim == null) ? LocalDate.now() : fim);
      return ResponseEntity.ok(result);
   }
   
   // Método para buscar dados ambientais por id
   @GetMapping("/{id}") // GET /api/dados-ambientais/{id}
   public ResponseEntity<DadosAmbientais> getDadosAmbientaisById(@PathVariable Long id) {
      Optional<DadosAmbientais> dadosAmbientais = service.getDadosAmbientaisById(id);
      return dadosAmbientais.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
