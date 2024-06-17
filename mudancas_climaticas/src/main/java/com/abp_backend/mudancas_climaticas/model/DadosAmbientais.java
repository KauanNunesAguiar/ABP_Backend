package com.abp_backend.mudancas_climaticas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dados_ambientais")
public class DadosAmbientais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    private LocalDateTime dataHora;
    private String localizacao;
    
    @Enumerated(EnumType.STRING)
    private TipoDado tipoDado;
    
    private Double valor;


    public DadosAmbientais() {
    }

    public DadosAmbientais(Long id, Sensor sensor, LocalDateTime dataHora, TipoDado tipoDado, Double valor) {
        this.id = id;
        this.sensor = sensor;
        this.dataHora = dataHora;
        this.localizacao = sensor.getLocalizacao();
        this.tipoDado = tipoDado;
        this.valor = valor;
        
    }

    public DadosAmbientais(Sensor sensor, TipoDado tipoDado, Double valor) {
        this.sensor = sensor;
        this.dataHora = LocalDateTime.now();
        this.localizacao = sensor.getLocalizacao();
        this.tipoDado = tipoDado;
        this.valor = valor;
    }

    // Getters e Setters --------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    public TipoDado getTipoDado() {
      return tipoDado;
   }

   public void setTipoDado(TipoDado tipoDado) {
      this.tipoDado = tipoDado;
  }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    
}
