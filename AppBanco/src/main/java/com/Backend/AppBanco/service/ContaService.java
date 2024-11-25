package com.Backend.AppBanco.service;

import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.entity.UsuarioEntity;
import com.Backend.AppBanco.repository.ContaRepository;
import com.Backend.AppBanco.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ContaEntity criarConta(String nomeTitular, Integer idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ContaEntity conta = new ContaEntity(nomeTitular, usuario);
        return contaRepository.save(conta);
    }

    public ContaEntity buscarContaPorId(Integer idConta) {
        return contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public List<ContaEntity> listarContas() {
        return contaRepository.findAll();
    }

    public BigDecimal consultarSaldo(Integer idConta) {
        ContaEntity conta = buscarContaPorId(idConta);
        return conta.getSaldo();
    }

    public ContaEntity realizarDeposito(Integer idConta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor do depósito deve ser positivo");
        }

        ContaEntity conta = buscarContaPorId(idConta);
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }

    public ContaEntity realizarSaque(Integer idConta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor do saque deve ser positivo");
        }

        ContaEntity conta = buscarContaPorId(idConta);
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        return contaRepository.save(conta);
    }
}
