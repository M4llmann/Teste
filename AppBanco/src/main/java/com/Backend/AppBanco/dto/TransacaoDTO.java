package com.Backend.AppBanco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDTO {
    private Integer idTransacao;
    private Integer idConta;
    private String tipo;  // Considere usar um Enum aqui para controle de tipos
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    // Construtores, Getters e Setters

    public TransacaoDTO() {}

    public TransacaoDTO(Integer idTransacao, Integer idConta, String tipo, BigDecimal valor, LocalDateTime dataTransacao) {
        this.idTransacao = idTransacao;
        this.idConta = idConta;
        this.tipo = tipo;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
