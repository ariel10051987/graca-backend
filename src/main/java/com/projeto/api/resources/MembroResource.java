package com.projeto.api.resources;

import com.projeto.api.domain.secretaria.Membro;
import com.projeto.api.dto.secretaria.MembroDto;
import com.projeto.api.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/membro")
public class MembroResource {

    @Autowired
    private MembroService service;

    @Value("${diretorio.upload}")
    private String diretorio;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Membro obj = service.findById(id);
        MembroDto objDto = new MembroDto(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<Page<MembroDto>> findPage(
            @RequestParam(value = "busca", defaultValue = "") String busca,
            @RequestParam(value = "mes", defaultValue = "") String mes,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Membro> list = service.findPage(busca, mes, page, linesPerPage, direction, orderBy);
        Page<MembroDto> listDto = list.map(obj -> new MembroDto(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Membro obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdMembro()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Membro obj, @PathVariable Integer id) {
        obj.setIdMembro(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity downloadFileFromLocal(@PathVariable Integer id) {

        Membro obj = service.findById(id);
        Path path = Paths.get(this.diretorio + "membro/foto3x4.jpg");

        if (obj.getFoto() != "") {
            Path foto = Paths.get(this.diretorio + obj.getFoto());
            File file = foto.toFile();
            if(file.isFile()) {
                path = foto;
            }
        }

        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
