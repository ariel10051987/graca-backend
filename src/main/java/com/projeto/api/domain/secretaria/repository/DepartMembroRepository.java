package com.projeto.api.domain.secretaria.repository;

import com.projeto.api.domain.secretaria.DepartMembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartMembroRepository extends JpaRepository<DepartMembro,Integer> {

}