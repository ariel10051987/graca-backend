package com.projeto.api.security;

import com.projeto.api.domain.seguranca.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserSS implements UserDetails {

    private Integer id;
    private String  nome;
    private String  email;
    private String  senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {}

    public UserSS(Integer id, String nome, String email, String senha, Perfil perfil) {
        this.id          = id;
        this.nome        = nome;
        this.email       = email;
        this.senha       = senha;
        this.authorities = Collections.singleton( new SimpleGrantedAuthority( perfil.getDcPerfil() ) );
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
