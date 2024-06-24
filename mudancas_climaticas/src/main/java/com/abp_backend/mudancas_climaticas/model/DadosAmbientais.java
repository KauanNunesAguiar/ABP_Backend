package com.abp_backend.mudancas_climaticas.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

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
   private TipoSensor tipoDado;

   private Double valor;
   
   DadosAmbientais() {
   }
   
   public DadosAmbientais(Sensor sensor, LocalDateTime dataHora, String localizacao, TipoSensor tipoDado, Double valor) {
      this.sensor = sensor;
      this.dataHora = dataHora;
      this.localizacao = localizacao;
      this.tipoDado = tipoDado;
      this.valor = valor;
   }
   
   public DadosAmbientais(Sensor sensor, TipoSensor tipoDado, Double valor) {
      this.sensor = sensor;
      this.dataHora = LocalDateTime.now();
      this.localizacao = sensor.getLocalizacao();
      this.tipoDado = tipoDado;
      this.valor = valor;
  }
   
   // Getters e Setters --------------------------------------------------------
   public void setId(Long id) {
      this.id = id;
   }
   
   public Long getId() {
      return id;
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
   
   public TipoSensor getTipoDado() {
      return tipoDado;
   }
   
   public void setTipoDado(TipoSensor tipoDado) {
      this.tipoDado = tipoDado;
   }
   
   public Double getValor() {
      return valor;
   }
   
   public void setValor(Double valor) {
      this.valor = valor;
   }
}
