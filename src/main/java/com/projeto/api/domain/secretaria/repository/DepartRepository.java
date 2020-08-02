package com.projeto.api.domain.secretaria.repository;

import com.projeto.api.domain.secretaria.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartRepository extends JpaRepository<Depart,Integer> {
}