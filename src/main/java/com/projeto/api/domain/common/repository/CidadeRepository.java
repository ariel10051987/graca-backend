package com.projeto.api.domain.common.repository;

import com.projeto.api.domain.common.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

    @Query(value = "SELECT obj FROM Cidade obj WHERE obj.nome like %:busca%")
    Page<Cidade> search(@Param("busca") String busca, Pageable pageRequest);
}