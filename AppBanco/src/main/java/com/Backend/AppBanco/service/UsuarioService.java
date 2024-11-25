package com.Backend.AppBanco.service;

import com.Backend.AppBanco.entity.UsuarioEntity;
import com.Backend.AppBanco.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity criarUsuario(@Valid String email, @Valid String senha) {
        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        UsuarioEntity usuario = new UsuarioEntity(email, senha);
        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}
