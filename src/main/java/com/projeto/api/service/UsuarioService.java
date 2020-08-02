package com.projeto.api.service;

import com.projeto.api.domain.seguranca.Perfil;
import com.projeto.api.domain.seguranca.Usuario;
import com.projeto.api.domain.seguranca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder crypt;

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private static UsuarioRepository staticRepo;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UploadService uploadService;

    @Value("${diretorio.assinatura}")
    private String dirAssinatura;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = repo.findByEmail(email);
        return obj.orElse(null);
    }

    public Usuario findByCpf(String cpf) {
        Optional<Usuario> obj = repo.findByCpf(cpf);
        return obj.orElse(null);
    }

    public Page<Usuario> findPage(String busca, Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageResquest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        if (busca.equals(null)) {
            return repo.findAll(pageResquest);
        }
        return repo.search(busca, pageResquest);
    }

    public Usuario insert(Usuario obj) {

        obj.setIdUsuario(null);
        obj.setDtInclusao(new Date());
        obj.setSenha(crypt.encode(obj.getSenha()));

        int idPerfil = 2;
        if (obj.getPerfil() != null) {
            idPerfil = obj.getPerfil().getIdPerfil();
        }

        Perfil perfil = perfilService.findById(idPerfil);
        obj.setPerfil(perfil);

        //upload da assinatura
        if (obj.getBase64Imagem() != null
            && obj.getNameBase64Imagem() != null
            && obj.getCpf() != null
        ) {
            uploadService.upload(this.dirAssinatura,  obj.getCpf() + "_" + obj.getNameBase64Imagem(), obj.getBase64Imagem());
            obj.setUrlAssinatura(this.dirAssinatura + obj.getCpf() + "_" + obj.getNameBase64Imagem());
        }

        return repo.save(obj);
    }

    public Usuario update(Usuario obj) {
        Usuario usuario = findById(obj.getIdUsuario());
        if (obj.getSenha() == null) {
            obj.setSenha(usuario.getSenha());
        } else {
            obj.setSenha(crypt.encode(obj.getSenha()));
        }

        //upload da assinatura
        if (obj.getBase64Imagem() != null
            && obj.getNameBase64Imagem() != null
            && obj.getCpf() != null
        ) {
            uploadService.upload(this.dirAssinatura,  obj.getCpf() + "_" + obj.getNameBase64Imagem(), obj.getBase64Imagem());
            obj.setUrlAssinatura(this.dirAssinatura + obj.getCpf() + "_" + obj.getNameBase64Imagem());
        }

        return repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public void lembrarSenha(String email) {
        Usuario obj = findByEmail(email);
        if (obj.getEmail() != null) {
            obj.setSenha(crypt.encode("123456"));
            repo.save(obj);
        }
    }
}