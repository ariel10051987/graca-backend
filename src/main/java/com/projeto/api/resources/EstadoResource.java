package com.projeto.api.resources;

import com.projeto.api.domain.common.Estado;
import com.projeto.api.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estado")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
