package com.projeto.api.service;

import com.projeto.api.domain.secretaria.Membro;
import com.projeto.api.domain.secretaria.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository repo;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private AbstractEmailService emailService;

    public Membro findById(Integer id) {
        Optional<Membro> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public Membro findByCpf(String cpf) {
        Optional<Membro> obj = repo.findByCpf(cpf);
        return obj.orElse(null);
    }

    public Membro findByIdentidade(String identidade) {
        Optional<Membro> obj = repo.findByIdentidade(identidade);
        return obj.orElse(null);
    }

    public Page<Membro> findPage(String busca, String mes, Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageResquest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        if (!busca.isEmpty()) {
            return repo.search(busca.toLowerCase(), pageResquest);
        } else if (!mes.isEmpty()) {
            return repo.nascimento(mes.toLowerCase(), pageResquest);
        } else {
            return repo.findAll(pageResquest);
        }
    }

    public Membro insert(Membro obj) {
        return obj;
//        obj.setIdMembro(null);
//        obj.getDepartMembros().stream().forEach((departMembro) -> departMembro.setMembro(obj));
//        return repo.save(obj);
    }

    public Membro update(Membro obj) {
        Membro membro = findById(obj.getIdMembro());

        if (obj.getCodigoBase64() != null && obj.getNameFileBase64() != null) {
            uploadService.upload("membro/" + membro.getIdMembro(), obj.getNameFileBase64(), obj.getCodigoBase64());
            obj.setFoto("membro/" + membro.getIdMembro() + "/" + obj.getNameFileBase64());
        }

        // Depart
        obj.getDepartMembros().stream().forEach((departMembro) -> departMembro.setMembro(obj));
        return repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void agendamento() {
//        emailService.sendEmail();
        System.out.print(" Aqui ! em cada 5 min");
    }
}