package com.projeto.api.resources;

import com.projeto.api.domain.secretaria.Igreja;
import com.projeto.api.service.IgrejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/igreja")
public class IgrejaResource {

    @Autowired
    private IgrejaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Igreja obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Igreja>> findAll() {
        List<Igreja> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
