package com.projeto.api.service.Validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projeto.api.resources.exception.FieldMessage;
import com.projeto.api.service.UsuarioService;
import com.projeto.api.service.Validation.utils.ValidaFormato;
import com.projeto.api.domain.seguranca.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioRegra implements ConstraintValidator<UsuarioInterface, Usuario> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void initialize(UsuarioInterface ann) {}

    @Override
    public boolean isValid(Usuario obj, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // validar cpf
        if (obj.getCpf() != "" && !ValidaFormato.isValidCPF(obj.getCpf())) {
            list.add(new FieldMessage("cpf", "CPF inválido"));
        }
        // validar cpf duplicado
        if (isExisteCpf(obj)) {
            list.add(new FieldMessage("cpf", "Já cadastro em nossa base de dados"));
        }
        // validar email
        if (!ValidaFormato.isValidEmail(obj.getEmail())) {
            list.add(new FieldMessage("email", "E-mail inválido"));
        }
        // validar email duplicado
        if (isExisteEmail(obj)) {
            list.add(new FieldMessage("email", "Já cadastro em nossa base de dados"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField()).addConstraintViolation();
        }

        return list.isEmpty();
    }

    public boolean isExisteCpf(Usuario obj) {
        if (obj.getCpf() != "") {
            if (usuarioService instanceof UsuarioService) {
                Usuario usuario = usuarioService.findByCpf(obj.getCpf());
                if ((usuario != null && obj.getIdUsuario() == null)
                        || usuario != null && !obj.getIdUsuario().equals(usuario.getIdUsuario())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isExisteEmail(Usuario obj) {
        if (usuarioService instanceof UsuarioService) {
            Usuario usuario = usuarioService.findByEmail(obj.getEmail());
            if ((usuario != null && obj.getIdUsuario() == null)
                || usuario != null && !obj.getIdUsuario().equals(usuario.getIdUsuario())) {
                return true;
            }
        }
        return false;
    }
}