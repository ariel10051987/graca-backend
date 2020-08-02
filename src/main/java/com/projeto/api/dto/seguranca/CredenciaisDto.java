package com.projeto.api.dto.seguranca;

import java.io.Serializable;

public class CredenciaisDto implements Serializable {

    private String email;
    private String senha;

    public  CredenciaisDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
