package com.abp_backend.mudancas_climaticas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensor_ambiente")
public class Sensor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private int tipos;
   private String descricao;
   private String localizacao;

   public Sensor() {
   }
   
   public Sensor(Long id, int tipos, String localizacao) {
      this.id = id;
      this.tipos = tipos;
      this.localizacao = localizacao;
      this.defineDescricao();
   }

   public Sensor(Long id, int tipos, String descricao, String localizacao) {
      this.id = id;
      this.tipos = tipos;
      this.descricao = descricao;
      this.localizacao = localizacao;
   }
   
   public void defineDescricao() {
      this.descricao = "Sensor ";
      
      if (TipoSensor.hasType(tipos, TipoSensor.TEMPERATURA)) {
         this.descricao += "T";
      }
      if (TipoSensor.hasType(tipos, TipoSensor.UMIDADE)) {
         this.descricao += "U";
      }
      if (TipoSensor.hasType(tipos, TipoSensor.CO2)) {
         this.descricao += "C";
      }
      this.descricao += " - " + this.localizacao + " - " + " " + (int) (Math.random() * 1000);
   }

   // Getters e Setters --------------------------------------------------------
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getTipos() {
      return tipos;
   }

   public void setTipos(int tipos) {
      this.tipos = tipos;
   }

   public void addTipo(TipoSensor tipo) {
      this.tipos = TipoSensor.addType(this.tipos, tipo);
   }

   public void removeTipo(TipoSensor tipo) {
      this.tipos = TipoSensor.removeType(this.tipos, tipo);
   }

   public boolean hasTipo(TipoSensor tipo) {
      return TipoSensor.hasType(this.tipos, tipo);
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public String getLocalizacao() {
      return localizacao;
   }

   public void setLocalizacao(String localizacao) {
      this.localizacao = localizacao;
   }

   public DadosAmbientais coletarDados(TipoDado tipoDado, Double valor) {
        if (this.hasTipo(TipoSensor.valueOf(tipoDado.name()))) {
            return new DadosAmbientais(this, tipoDado, valor);
        }
        return null;
    }
}
