package com.projeto.api.service;

import com.projeto.api.domain.seguranca.Usuario;
import com.projeto.api.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceSS implements UserDetailsService {

    @Autowired
    private UsuarioService service;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = service.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(user.getIdUsuario(), user.getNome(), user.getEmail(), user.getSenha(), user.getPerfil());
    }
}
