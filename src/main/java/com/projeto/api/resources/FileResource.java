
package com.projeto.api.resources;

import com.projeto.api.service.UploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/file")
public class FileResource {

    @Autowired
    private UploadService uploadService;

    @Value("${diretorio.upload}")
    private String diretorio;

    @GetMapping(value = "/download")
    public HttpEntity<byte[]> download(@RequestParam(value = "file") String file) throws IOException {
        byte[] arquivo = Files.readAllBytes( Paths.get(diretorio + file) );

        String ext = FilenameUtils.getExtension(diretorio + file);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=\"download."+ ext +"\"");
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
        return entity;
    }
}
