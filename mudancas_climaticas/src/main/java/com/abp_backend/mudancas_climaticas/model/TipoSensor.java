package com.abp_backend.mudancas_climaticas.model;

public enum TipoSensor {
   TEMPERATURA(1, -20.0, 60.0),
   UMIDADE(2, 0.0, 100.0),
   CO2(4, 0, 2000),
   LUMINOSIDADE(8, 0, 1000);

   public final int valor;
   public final double valorMinimo;
   public final double valorMaximo;

   TipoSensor(int valor, double valorMinimo, double valorMaximo) {
      this.valor = valor;
      this.valorMinimo = valorMinimo;
      this.valorMaximo = valorMaximo;
   }
   
   public boolean isInRange(double valor) {
      return valor >= valorMinimo && valor <= valorMaximo;
   }

   public static boolean hasType(int combinedTypes, TipoSensor type) {
      return (combinedTypes & type.valor) == type.valor;
   }

   public static int addType(int combinedTypes, TipoSensor type) {
      return combinedTypes | type.valor;
   }

   public static int removeType(int combinedTypes, TipoSensor type) {
      return combinedTypes & ~type.valor;
   }
   
   public double getValorMinimo() {
      return valorMinimo;
   }
   
   public double getValorMaximo() {
      return valorMaximo;
   }
}
