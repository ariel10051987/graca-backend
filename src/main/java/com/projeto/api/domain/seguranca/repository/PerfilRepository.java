package com.projeto.api.domain.seguranca.repository;

import com.projeto.api.domain.seguranca.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil,Integer> {
}