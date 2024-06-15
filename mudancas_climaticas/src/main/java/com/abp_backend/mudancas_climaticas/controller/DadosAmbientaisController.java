package com.abp_backend.mudancas_climaticas.controller;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.service.DadosAmbientaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/dados-ambientais")
public class DadosAmbientaisController {

    @Autowired
    private DadosAmbientaisService service;

    @GetMapping
    public ResponseEntity<Page<DadosAmbientais>> getAllDadosAmbientais(Pageable pageable) {
        Page<DadosAmbientais> dadosAmbientaisPage = service.getAllDadosAmbientais(pageable);
        return ResponseEntity.ok(dadosAmbientaisPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosAmbientais> getDadosAmbientaisById(@PathVariable Long id) {
        Optional<DadosAmbientais> dadosAmbientais = service.getDadosAmbientaisById(id);
        return dadosAmbientais.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DadosAmbientais> createDadosAmbientais(@RequestBody DadosAmbientais dadosAmbientais) {
        DadosAmbientais createdDadosAmbientais = service.saveDadosAmbientais(dadosAmbientais);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDadosAmbientais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosAmbientais> updateDadosAmbientais(@PathVariable Long id, @RequestBody DadosAmbientais dadosAmbientais) {
        Optional<DadosAmbientais> updatedDadosAmbientais = service.updateDadosAmbientais(id, dadosAmbientais);
        return updatedDadosAmbientais.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDadosAmbientais(@PathVariable Long id) {
        if (service.deleteDadosAmbientais(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
