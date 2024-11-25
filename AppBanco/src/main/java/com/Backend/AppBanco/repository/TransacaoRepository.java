package com.Backend.AppBanco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Backend.AppBanco.entity.TransacaoEntity;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {

    // Método customizado para encontrar transações por idConta
    List<TransacaoEntity> findByContaIdConta(Integer idConta);
}
