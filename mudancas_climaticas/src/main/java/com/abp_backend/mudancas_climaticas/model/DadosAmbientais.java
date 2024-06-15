package com.abp_backend.mudancas_climaticas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dados_ambientais")
public class DadosAmbientais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String localizacao;
    private double temperatura;
    private double umidade;
    private int co2;

    public DadosAmbientais() {
    }

   public DadosAmbientais(Long id, String localizacao, double temperatura, double umidade, int co2) {
      this.id = id;
      this.localizacao = localizacao;
      this.temperatura = temperatura;
      this.umidade = umidade;
      this.co2 = co2;
   }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }
    
   public int getCo2() {
      return co2;
   }
   
   public void setCo2(int co2) {
      this.co2 = co2;
   }
}
