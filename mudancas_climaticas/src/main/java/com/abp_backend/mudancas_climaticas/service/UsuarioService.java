package com.abp_backend.mudancas_climaticas.service;

import com.abp_backend.mudancas_climaticas.model.Usuario;
import com.abp_backend.mudancas_climaticas.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
   
   @Autowired
   private UsuarioRepository repository;

   @PersistenceContext
   private EntityManager entityManager;
   
   public Page<Usuario> getAllUsuarios(Pageable pageable) {
      return repository.findAll(pageable);
   }

   public Usuario saveUsuario(Usuario usuario) {
      return repository.save(usuario);
   }
   
   public Optional<Usuario> getUsuarioById(Long id) {
      return repository.findById(id);
   }
   
   public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
      Optional<Usuario> existingUsuario = repository.findById(id);
      if (existingUsuario.isPresent()) {
         usuario.setId(id);
         return Optional.of(repository.save(usuario));
      }
      return Optional.empty();
   }
   
   public boolean deleteUsuario(Long id) {
      if (repository.existsById(id)) {
         repository.deleteById(id);
         return true;
      }
      return false;
   }
   
   public void resetDatabase() {
      repository.deleteAll();
      entityManager.createNativeQuery("ALTER TABLE usuario ALTER COLUMN id RESTART WITH 1").executeUpdate();
   }
}