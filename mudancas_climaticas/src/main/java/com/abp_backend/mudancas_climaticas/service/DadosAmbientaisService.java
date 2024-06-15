package com.abp_backend.mudancas_climaticas.service;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import com.abp_backend.mudancas_climaticas.repository.DadosAmbientaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DadosAmbientaisService {

    @Autowired
    private DadosAmbientaisRepository repository;

    public Page<DadosAmbientais> getAllDadosAmbientais(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<DadosAmbientais> getDadosAmbientaisById(Long id) {
        return repository.findById(id);
    }

    public DadosAmbientais saveDadosAmbientais(DadosAmbientais dados) {
        return repository.save(dados);
    }

    public Optional<DadosAmbientais> updateDadosAmbientais(Long id, DadosAmbientais dados) {
        return repository.findById(id).map(existingDados -> {
            dados.setId(id);
            return repository.save(dados);
        });
    }

    public boolean deleteDadosAmbientais(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
