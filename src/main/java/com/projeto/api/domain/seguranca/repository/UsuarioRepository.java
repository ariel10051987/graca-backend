package com.projeto.api.domain.seguranca.repository;

import com.projeto.api.domain.seguranca.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    @Query(value = "SELECT obj FROM Usuario obj WHERE obj.nome like %:busca% OR obj.email like %:busca%")
    Page<Usuario> search (@Param("busca") String busca, Pageable pageRequest);
}