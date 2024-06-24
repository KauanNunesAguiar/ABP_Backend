package com.abp_backend.mudancas_climaticas.controller;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.model.Sensor;
import com.abp_backend.mudancas_climaticas.model.Usuario;
import com.abp_backend.mudancas_climaticas.service.DadosAmbientaisService;
import com.abp_backend.mudancas_climaticas.service.SensorService;
import com.abp_backend.mudancas_climaticas.service.UsuarioService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;

   @Autowired
   private SensorService sensorService;
   
   @Autowired
   private DadosAmbientaisService dadosAmbientaisService;

   // Métodos para CRUD de Usuários --------------------------------------------
   // Método para listar todos os usuários
   @GetMapping // GET /api/usuarios
   public ResponseEntity<Page<Usuario>> getAllUsuarios(Pageable pageable) {
      Page<Usuario> usuariosPage = usuarioService.getAllUsuarios(pageable);
      return ResponseEntity.ok(usuariosPage);
   }

   // Método para buscar usuário por id
   @GetMapping("/{id}") // GET /api/usuarios/{id}
   public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
      return usuarioService.getUsuarioById(id)
              .map(usuario -> ResponseEntity.ok().body(usuario))
              .orElse(ResponseEntity.notFound().build());
   }

   // Método para atualizar usuário
   @PutMapping("/{id}") // PUT /api/usuarios/{id}
   public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
      Optional<Usuario> updatedUsuario = usuarioService.updateUsuario(id, usuario);
      return updatedUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   // Método para deletar usuário
   @DeleteMapping("/{id}") // DELETE /api/usuarios/{id}
   public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
      if (usuarioService.deleteUsuario(id)) return ResponseEntity.ok().build();
      return ResponseEntity.notFound().build();
   }

   // Método para criar usuário
   @PostMapping // POST /api/usuarios
   public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
      Usuario createdUsuario = usuarioService.saveUsuario(usuario);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
   }

   // Método para cadastrar sensor em um usuário
   @PostMapping("/{usuarioId}/register-sensor/{sensorId}") // POST /api/usuarios/{usuarioId}/register-sensor/{sensorId}
   public ResponseEntity<Usuario> registerSensor(@PathVariable Long usuarioId, @PathVariable Long sensorId) {
      Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(usuarioId);
      if (usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();

      Optional<Sensor> sensorOptional = sensorService.getSensorById(sensorId);
      if (sensorOptional.isEmpty()) return ResponseEntity.notFound().build();

      Usuario usuario = usuarioOptional.get();
      Sensor sensor = sensorOptional.get();
      
      sensor.defineDescricao();
      
      sensor.setUsuario(usuario);
      sensorService.saveSensor(sensor);

      return ResponseEntity.ok(usuario);
   }
   
   // Método para buscar sensores de um usuário
   @GetMapping("/{usuarioId}/sensors") // GET /api/usuarios/{usuarioId}/sensors
   public ResponseEntity<List<Sensor>> getSensorsByUsuario(@PathVariable Long usuarioId) {
      
      Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(usuarioId);
      if (usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();
      
      List<Sensor> sensorsPage = sensorService.getSensorsByUsuario(usuarioId);
      return ResponseEntity.ok(sensorsPage);
   }
   
   // Método para remover sensor de um usuário
   @DeleteMapping("/{usuarioId}/delete-sensor/{sensorId}") // DELETE /api/usuarios/{usuarioId}/delete-sensor/{sensorId}
   public ResponseEntity<Usuario> unregisterSensor(@PathVariable Long usuarioId, @PathVariable Long sensorId) {
      Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(usuarioId);
      if (usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();

      Optional<Sensor> sensorOptional = sensorService.getSensorById(sensorId);
      if (sensorOptional.isEmpty()) return ResponseEntity.notFound().build();

      Usuario usuario = usuarioOptional.get();
      Sensor sensor = sensorOptional.get();
      
      sensor.setUsuario(null);
      sensorService.saveSensor(sensor);

      return ResponseEntity.ok(usuario);
   }
   
   // Método para buscar dados ambientais por sensor e/ou intervalo de tempo
   @GetMapping("/{usuarioId}/search-by-sensor/{sensorId}") // GET /api/usuarios/{usuarioId}/search-by-sensor/{sensorId}
   public ResponseEntity<List<DadosAmbientais>> searchDadosAmbientaisBySensor(
        @PathVariable Long usuarioId,
        @PathVariable Long sensorId,
        @RequestParam(required = false) LocalDate inicio,
        @RequestParam(required = false) LocalDate fim) {
    
      LocalDate dataInicioDefault = LocalDate.of(2000, 1, 1);
    
      Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(usuarioId);
      Optional<Sensor> sensorOptional = sensorService.getSensorById(sensorId);
    
      if (usuarioOptional.isEmpty() || sensorOptional.isEmpty()) return ResponseEntity.badRequest().build();
      if (sensorOptional.get().getUsuario().getId() != usuarioId) return ResponseEntity.badRequest().build();
    
      List<DadosAmbientais> result = dadosAmbientaisService.buscarPorSensorEIntervaloDeTempo(sensorId, (inicio == null) ? dataInicioDefault : inicio, (fim == null) ? LocalDate.now() : fim);
      return ResponseEntity.ok(result);
   }

   // Método para buscar dados ambientais por localização e/ou intervalo de tempo
   @GetMapping("/{usuarioId}/search-by-location/{localizacao}") // GET /api/usuarios/{usuarioId}/search-by-location/{localizacao}
   public ResponseEntity<List<DadosAmbientais>> searchDadosAmbientaisByLocation(
      @PathVariable Long usuarioId, // {usuarioId}
      @PathVariable String localizacao, // {localizacao}
      @RequestParam(required = false) LocalDate inicio, // &inicio=yyyy-MM-dd
      @RequestParam(required = false) LocalDate fim, // &fim=yyyy-MM-dd
      Pageable pageable) {
      
      LocalDate dataInicioDefault = LocalDate.of(2000, 1, 1);
      
      List<DadosAmbientais> result = dadosAmbientaisService.buscarPorLocalizacaoEIntervaloDeTempo(localizacao, (inicio == null) ? dataInicioDefault : inicio, (fim == null) ? LocalDate.now() : fim);
      return ResponseEntity.ok(result);
   }
   
   // Método para resetar o banco de dados
   @DeleteMapping("/reset") // DELETE /api/usuarios/reset
   @Transactional
   public ResponseEntity<Void> resetDatabase() {
      usuarioService.resetDatabase();
      return ResponseEntity.ok().build();
   }
}
