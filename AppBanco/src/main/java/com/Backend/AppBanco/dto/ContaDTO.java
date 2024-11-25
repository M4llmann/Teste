package com.Backend.AppBanco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaDTO {
    private Integer idConta;
    private String nomeTitular;
    private BigDecimal saldo = BigDecimal.ZERO;  // Inicializado com zero
    private Boolean status = true;  // Definido como true por padrão
    private LocalDateTime dataCriacao;
    private Integer idUsuario;

    // Construtor padrão
    public ContaDTO() {}

    // Construtor completo
    public ContaDTO(Integer idConta, String nomeTitular, BigDecimal saldo, Boolean status, LocalDateTime dataCriacao, Integer idUsuario) {
        this.idConta = idConta;
        this.nomeTitular = nomeTitular;
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;  // Garantindo que o saldo seja zero se não for informado
        this.status = status != null ? status : true;  // Garantindo que o status seja true se não for informado
        this.dataCriacao = dataCriacao;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public Integer getIdConta() { return idConta; }
    public void setIdConta(Integer idConta) { this.idConta = idConta; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
}
