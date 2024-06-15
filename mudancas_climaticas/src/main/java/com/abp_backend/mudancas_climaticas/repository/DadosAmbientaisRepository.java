package com.abp_backend.mudancas_climaticas.repository;

import com.abp_backend.mudancas_climaticas.model.DadosAmbientais;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DadosAmbientaisRepository {
    private List<DadosAmbientais> dados = new ArrayList<>();
    private AtomicLong counter = new AtomicLong(0);

    public DadosAmbientaisRepository() {
        // Adiciona dados fictícios para teste
        dados.add(new DadosAmbientais(counter.incrementAndGet(), "São Paulo", 25.5, 80));
        dados.add(new DadosAmbientais(counter.incrementAndGet(), "Rio de Janeiro", 30.2, 70));
    }

    public List<DadosAmbientais> findAll() {
        return dados;
    }

    public Optional<DadosAmbientais> findById(Long id) {
        return dados.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public DadosAmbientais save(DadosAmbientais dadosAmbientais) {
        if (dadosAmbientais.getId() == null) {
            dadosAmbientais.setId(counter.incrementAndGet());
            dados.add(dadosAmbientais);
        } else {
            deleteById(dadosAmbientais.getId());
            dados.add(dadosAmbientais);
        }
        return dadosAmbientais;
    }

    public void deleteById(Long id) {
        dados.removeIf(d -> d.getId().equals(id));
    }
}
