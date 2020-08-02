package com.projeto.api.service.Validation;

import com.projeto.api.domain.secretaria.Membro;
import com.projeto.api.resources.exception.FieldMessage;
import com.projeto.api.service.MembroService;
import com.projeto.api.service.Validation.utils.ValidaFormato;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

    public class MembroRegra implements ConstraintValidator<MembroInterface, Membro> {

    @Autowired
    private MembroService membroService;

    @Override
    public void initialize(MembroInterface ann) {}

    @Override
    public boolean isValid(Membro obj, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

//        // validar cpf
//        if (obj.getCpf() != "" && !ValidaFormato.isValidCPF(obj.getCpf())) {
//            list.add(new FieldMessage("cpf", "CPF inválido"));
//        }
//        // validar cpf duplicado
//        if (isExisteCpf(obj)) {
//            list.add(new FieldMessage("cpf", "Já cadastro em nossa base de dados"));
//        }

        // validar identidade duplicado
        if (isExisteRg(obj)) {
            list.add(new FieldMessage("identidade", "Já cadastro em nossa base de dados"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField()).addConstraintViolation();
        }

        return list.isEmpty();
    }

    public boolean isExisteCpf(Membro obj) {
        if (obj.getCpf() !="") {
            if (membroService instanceof MembroService) {
                Membro membro = membroService.findByCpf(obj.getCpf());
                if ((membro != null && obj.getIdMembro() == null)
                        || membro != null && !obj.getIdMembro().equals(membro.getIdMembro())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isExisteRg(Membro obj) {
        if (membroService instanceof MembroService) {
            if (obj.getIdentidade() != "") {
                Membro membro = membroService.findByIdentidade(obj.getIdentidade());
                if ((membro != null && obj.getIdMembro() == null)
                        || membro != null && !obj.getIdMembro().equals(membro.getIdMembro())) {
                    return true;
                }
            }
        }
        return false;
    }
}