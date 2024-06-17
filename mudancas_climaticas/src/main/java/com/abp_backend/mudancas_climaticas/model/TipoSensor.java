package com.abp_backend.mudancas_climaticas.model;

public enum TipoSensor {
   TEMPERATURA(1),
   UMIDADE(2),
   CO2(4);

   public final int valor;

   TipoSensor(int valor) {
      this.valor = valor;
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
}
