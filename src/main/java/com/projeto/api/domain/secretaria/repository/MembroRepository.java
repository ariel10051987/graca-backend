package com.projeto.api.domain.secretaria.repository;

import com.projeto.api.domain.secretaria.Membro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends JpaRepository<Membro,Integer> {

    Optional<Membro> findById(Integer id);

    Optional<Membro> findByCpf(String cpf);

    Optional<Membro> findByIdentidade(String identidade);

    @Query(value = "SELECT obj FROM Membro obj WHERE to_char(obj.dtNascimento,'MM') = :mes")
    Page<Membro> nascimento(@Param("mes") String mes, Pageable pageRequest);

    @Query(value = "SELECT obj FROM Membro obj WHERE LOWER(obj.nome) like %:busca%")
    Page<Membro> search(@Param("busca") String busca, Pageable pageRequest);
}