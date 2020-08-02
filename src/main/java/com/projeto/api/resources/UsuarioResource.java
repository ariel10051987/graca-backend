package com.projeto.api.resources;

import com.projeto.api.domain.seguranca.Usuario;
import com.projeto.api.dto.seguranca.UsuarioDto;
import com.projeto.api.service.UploadService;
import com.projeto.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UploadService uploadService;

    @Value("${diretorio.upload}")
    private String diretorio;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/lembrarSenha/{email}")
    public ResponseEntity<Void> lembrarSenha(@PathVariable String email) {
        service.lembrarSenha(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<Page<Usuario>> findPage(
            @RequestParam(value = "busca", defaultValue = "") String busca,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Usuario> list = service.findPage(busca, page, linesPerPage, direction, orderBy);
        Page<UsuarioDto> listDto = list.map(obj -> new UsuarioDto(obj));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody Usuario obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable Integer id) {
        obj.setIdUsuario(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
