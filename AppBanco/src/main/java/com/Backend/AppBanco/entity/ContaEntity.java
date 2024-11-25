package com.Backend.AppBanco.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Conta")
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConta")
    private Integer idConta;

    @Column(name = "nomeTitular", nullable = false)
    private String nomeTitular;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "dataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuario;

    // Construtor padrão
    public ContaEntity() {}

    // Construtor com parâmetros
    public ContaEntity(String nomeTitular, UsuarioEntity usuario) {
        this.nomeTitular = nomeTitular;
        this.saldo = BigDecimal.ZERO; // Inicializa com zero
        this.status = true;
        this.dataCriacao = LocalDateTime.now();
        this.usuario = usuario;
    }

    // Getters e Setters
    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
