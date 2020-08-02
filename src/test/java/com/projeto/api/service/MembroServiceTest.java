package com.projeto.api.service;

import com.projeto.api.domain.secretaria.Membro;
import com.projeto.api.domain.secretaria.repository.MembroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MembroServiceTest {

    @Mock
    MembroRepository repo;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findById() {
        // Cenario
        Membro membro = Membro.builder().idMembro(1).build();
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(membro));

        // Execucao
        Optional<Membro> findById = repo.findById(1);

        // Verificacoes
        assertTrue( findById.isPresent() );
        assertTrue( findById.get().getIdMembro().equals(membro.getIdMembro()) );

        Mockito.verify(repo, Mockito.never()).save(membro);
    }
}