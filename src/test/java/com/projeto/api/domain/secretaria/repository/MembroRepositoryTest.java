package com.projeto.api.domain.secretaria.repository;

import com.projeto.api.domain.common.Cidade;
import com.projeto.api.domain.secretaria.Membro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(
        replace=AutoConfigureTestDatabase.Replace.NONE
)
@DataJpaTest
class MembroRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    MembroRepository repo;

    Membro membro;

    @DateTimeFormat
    private Date date;

    @BeforeAll
    void setUp() {
        date   = new Date();
    }

    @Test
    void findById() {
        Membro membro = Membro.builder()
                .nome("Fulano")
                .coCargo(1)
                .cidade(Cidade.builder().idCidade(1).build())
                .endereco("Endereco")
                .telefone("99999999999")
                .inEstadoCivil("1")
                .dtMembroDesde(date)
                .dtNascimento(date)
                .dtBatismo(date)
                .build();

        entityManager.persist(membro);

        // Execucao
        Optional<Membro> find = repo.findById(membro.getIdMembro());

        // Verificacao
         assertTrue( find.isPresent() );

//        Mockito.verify(repo, Mockito.atLeastOnce()).findById(membro.getIdMembro());
    }

//    @Test
//    void delete() {
//        // Cenario
//        int id = 1;
//        Membro membro = Membro.builder().idMembro(id).build();
//        entityManager.persist(membro);
//
//        // Execucao
//        Optional<Membro> findById = repo.findById(id);
//
//        // Verificacao
//        assertTrue( findById.isPresent() );
//    }
}