package com.abp_backend.mudancas_climaticas.model;

public class DadosAmbientais {
    private Long id;
    private String localizacao;
    private double temperatura;
    private double umidade;

    public DadosAmbientais(Long id, String localizacao, double temperatura, double umidade) {
        this.id = id;
        this.localizacao = localizacao;
        this.temperatura = temperatura;
        this.umidade = umidade;
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
}
