package com.abp_backend.mudancas_climaticas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_ambiente")
public class Sensor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @ManyToOne
   @JoinColumn(name = "usuario_id", referencedColumnName = "id")
   private Usuario usuario;
   
   private int tipos;
   private String descricao;
   private String localizacao;
   
   Sensor() {
   }
   
   public Sensor(Usuario usuario, int tipos, String descricao, String localizacao) {
      this.usuario = usuario;
      this.tipos = tipos;
      this.descricao = descricao;
      this.localizacao = localizacao;
   }
   
   public void defineDescricao() {
      this.descricao = "Sensor ";
      
      if (TipoSensor.hasType(this.tipos, TipoSensor.TEMPERATURA)) this.descricao += "T";
      if (TipoSensor.hasType(this.tipos, TipoSensor.UMIDADE)) this.descricao += "U";
      if (TipoSensor.hasType(this.tipos, TipoSensor.CO2)) this.descricao += "C";
      if (TipoSensor.hasType(this.tipos, TipoSensor.LUMINOSIDADE)) this.descricao += "L";

      this.descricao += " - " + this.localizacao + " - " + this.id;
   }
   
   public DadosAmbientais coletarDados(TipoSensor tipoDado, Double valor) {
      if (TipoSensor.hasType(this.tipos, tipoDado)) return new DadosAmbientais(this, tipoDado, valor);
      return null;
   }
   // Getters e Setters --------------------------------------------------------
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public int getTipos() {
      return tipos;
   }

   public void setTipos(int tipos) {
      this.tipos = tipos;
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
}
