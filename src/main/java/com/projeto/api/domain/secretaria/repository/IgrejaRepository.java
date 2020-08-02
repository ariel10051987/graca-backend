package com.projeto.api.domain.secretaria.repository;

import com.projeto.api.domain.secretaria.Igreja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IgrejaRepository extends JpaRepository<Igreja,Integer> {

//    Optional<Igreja> findById(Integer id);

    @Query(value = "SELECT obj FROM Igreja obj WHERE obj.nome like %:busca%")
    Page<Igreja> search(@Param("busca") String busca, Pageable pageRequest);

}