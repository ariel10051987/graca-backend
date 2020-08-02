package com.projeto.api.service;

import com.projeto.api.domain.seguranca.Perfil;
import com.projeto.api.domain.seguranca.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repo;

    public Perfil findById(Integer id) {
        Optional<Perfil> obj = repo.findById(id);
        return obj.orElse(null);
    }
}