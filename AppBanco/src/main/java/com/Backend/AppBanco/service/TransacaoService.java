package com.Backend.AppBanco.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.entity.TransacaoEntity;
import com.Backend.AppBanco.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    public TransacaoEntity criarTransacao(Integer idConta, String tipo, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor da transação deve ser positivo");
        }

        ContaEntity conta = contaService.buscarContaPorId(idConta);
        TransacaoEntity transacao = new TransacaoEntity(tipo, valor, conta);

        if ("deposito".equalsIgnoreCase(tipo)) {
            contaService.realizarDeposito(idConta, valor);
        } else if ("saque".equalsIgnoreCase(tipo)) {
            contaService.realizarSaque(idConta, valor);
        } else {
            throw new RuntimeException("Tipo de transação inválido");
        }

        return transacaoRepository.save(transacao);
    }

    public List<TransacaoEntity> buscarTransacoesPorConta(Integer idConta) {
        return transacaoRepository.findByContaIdConta(idConta);
    }

    public List<TransacaoEntity> obterExtrato(Integer idConta) {
        return buscarTransacoesPorConta(idConta);
    }
}
