package com.Backend.AppBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Backend.AppBanco.entity.ContaEntity;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {
    
}
