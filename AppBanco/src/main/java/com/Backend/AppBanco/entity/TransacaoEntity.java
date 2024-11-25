package com.Backend.AppBanco.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Transacao")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransacao")
    private Integer idTransacao;

    @ManyToOne
    @JoinColumn(name = "idConta", referencedColumnName = "idConta", nullable = false)
    private ContaEntity conta;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "dataTransacao", nullable = false)
    private LocalDateTime dataTransacao;

    // Construtor padrão
    public TransacaoEntity() {}

    // Construtor com parâmetros
    public TransacaoEntity(String tipo, BigDecimal valor, ContaEntity conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
        this.dataTransacao = LocalDateTime.now(); // Define a data atual para dataTransacao
    }

    // Getters e Setters
    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
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
