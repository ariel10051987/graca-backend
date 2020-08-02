package com.projeto.api.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class UploadService {

    @Value("${diretorio.upload}")
    private String diretorio;

    public void upload(String diretorio, String nome, String base64Bytes) {

        String diretorioPath = this.diretorio + diretorio;
        try {

            String ext = FilenameUtils.getExtension(diretorioPath+nome);

            String array[] = {"jpg", "jpeg", "png", "pdf"};

            if ( !Arrays.asList(array).contains(ext) ) {
                throw new RuntimeException("Tipo de arquivo não aceito pelo sistema!");
            }

            byte[] bytes = Base64.getDecoder().decode(base64Bytes.split(",")[1]);
            FileUtils.writeByteArrayToFile(new File(diretorioPath, nome), bytes);
        } catch (IOException e) {
            System.out.println( e.getMessage() );
            throw new RuntimeException("Problema na tentativa de salvar o arquivo." + e.getMessage());
        }
    }
}