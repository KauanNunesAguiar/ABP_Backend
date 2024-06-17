package com.abp_backend.mudancas_climaticas.model;
import static com.abp_backend.mudancas_climaticas.model.TipoDado.*;

public enum UnidadeDados {
   TEMPERATURA_DADO(TEMPERATURA, "Temperatura", -50.0, 50.0, -999.0),
   UMIDADE_DADO(UMIDADE, "Umidade", 0.0, 100.0, -999.0),
   CO2_DADO(CO2, "CO2", 0, 2000, -999);

   private final TipoDado tipoDado;
   private final String descricao;
   private final double valorMinimo;
   private final double valorMaximo;
   private final double valorDesabilitado;

   UnidadeDados(TipoDado tipoDado, String descricao, double valorMinimo, double valorMaximo, double valorDesabilitado) {
      this.tipoDado = tipoDado;
      this.descricao = descricao;
      this.valorMinimo = valorMinimo;
      this.valorMaximo = valorMaximo;
      this.valorDesabilitado = valorDesabilitado;
   }
   
   public TipoDado getTipoDado() {
      return tipoDado;
   }

   public String getDescricao() {
      return descricao;
   }

   public double getValorMinimo() {
      return valorMinimo;
   }

   public double getValorMaximo() {
      return valorMaximo;
   }

   public double getValorDesabilitado() {
      return valorDesabilitado;
   }
}
