package com.projeto.api.dto.seguranca;

import com.projeto.api.domain.seguranca.Usuario;

import java.io.Serializable;

public class UsuarioDto implements Serializable {

    private static final long serialVersionUID = 1;

    private Integer idUsuario;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario obj) {
        idUsuario     = obj.getIdUsuario();
    }

    public Integer getId() {
        return idUsuario;
    }

    public void setId(Integer id) {
        this.idUsuario = id;
    }

}
