package com.abp_backend.mudancas_climaticas.controller;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.repository.DadosAmbientaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dados-ambientais")
public class DadosAmbientaisController {

    @Autowired
    private DadosAmbientaisRepository repository;

    @GetMapping
    public List<DadosAmbientais> getDadosAmbientais() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosAmbientais> getDadosAmbientaisById(@PathVariable Long id) {
        Optional<DadosAmbientais> dadosAmbientais = repository.findById(id);
        return dadosAmbientais.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DadosAmbientais createDadosAmbientais(@RequestBody DadosAmbientais dadosAmbientais) {
        return repository.save(dadosAmbientais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosAmbientais> updateDadosAmbientais(@PathVariable Long id, @RequestBody DadosAmbientais dadosAmbientais) {
        Optional<DadosAmbientais> existingDados = repository.findById(id);
        if (existingDados.isPresent()) {
            dadosAmbientais.setId(id);
            return ResponseEntity.ok(repository.save(dadosAmbientais));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDadosAmbientais(@PathVariable Long id) {
        Optional<DadosAmbientais> existingDados = repository.findById(id);
        if (existingDados.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
