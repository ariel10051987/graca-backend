package com.projeto.api.service;

import com.projeto.api.domain.common.Cidade;
import com.projeto.api.domain.common.Estado;
import com.projeto.api.domain.common.repository.CidadeRepository;
import com.projeto.api.domain.common.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repoEstados;

    @Autowired
    private CidadeRepository repoCidades;

    public List<Estado> findAll() {
        return repoEstados.findAll();
    }
}