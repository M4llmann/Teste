package com.Backend.AppBanco.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Backend.AppBanco.dto.ContaDTO;
import com.Backend.AppBanco.dto.TransacaoDTO;
import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.service.ContaService;
import com.Backend.AppBanco.service.TransacaoService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;
    private final TransacaoService transacaoService;

public ContaController(ContaService contaService, TransacaoService transacaoService) {
    this.contaService = contaService;
    this.transacaoService = transacaoService;
}



    private ContaDTO toContaDTO(ContaEntity conta) {
        return new ContaDTO(
            conta.getIdConta(),
            conta.getNomeTitular(),
            conta.getSaldo(),
            conta.getStatus(),
            conta.getDataCriacao(),
            conta.getUsuario().getIdUsuario()
        );
    }
    
    @PostMapping("/{idUsuario}")
    public ResponseEntity<?> criarConta(@PathVariable Integer idUsuario, @RequestBody ContaDTO contaDTO) {
        try {
            ContaEntity conta = contaService.criarConta(contaDTO.getNomeTitular(), idUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(toContaDTO(conta));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{idConta}")
    public ResponseEntity<?> buscarConta(@PathVariable Integer idConta) {
        try {
            ContaEntity conta = contaService.buscarContaPorId(idConta);
            return ResponseEntity.ok(toContaDTO(conta));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{idConta}/saldo")
    public ResponseEntity<?> consultarSaldo(@PathVariable Integer idConta) {
        try {
            BigDecimal saldo = contaService.consultarSaldo(idConta);
            return ResponseEntity.ok(saldo);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    

    @PostMapping("/{idConta}/deposito")
    public ResponseEntity<?> realizarDeposito(@PathVariable Integer idConta, @RequestParam BigDecimal valor) {
        try {
            // Realiza o depósito diretamente com o valor obtido como parâmetro
            ContaEntity conta = contaService.realizarDeposito(idConta, valor);
            return ResponseEntity.ok(toContaDTO(conta));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @GetMapping("/{idConta}/extrato")
public ResponseEntity<?> obterExtrato(@PathVariable Integer idConta) {
    try {
        List<TransacaoDTO> extrato = transacaoService.obterExtrato(idConta).stream()
            .map(transacao -> new TransacaoDTO(
                transacao.getIdTransacao(),
                transacao.getConta().getIdConta(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDataTransacao()
            ))
            .toList();
        return ResponseEntity.ok(extrato);
    } catch (RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

    @PostMapping("/{idConta}/saque")
    public ResponseEntity<?> realizarSaque(@PathVariable Integer idConta, @RequestParam BigDecimal valor) {
        try {
            ContaEntity conta = contaService.realizarSaque(idConta, valor);
            return ResponseEntity.ok(toContaDTO(conta));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
