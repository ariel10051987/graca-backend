package com.projeto.api.service;

import com.projeto.api.domain.secretaria.Igreja;
import com.projeto.api.domain.secretaria.repository.IgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IgrejaService {

    @Autowired
    private IgrejaRepository repo;

    public Igreja findById(Integer id) {
        Optional<Igreja> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public List<Igreja> findAll() {
        return repo.findAll();
    }
}