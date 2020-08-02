package com.projeto.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.api.domain.common.Cidade;
import com.projeto.api.domain.secretaria.Membro;
import com.projeto.api.service.MembroService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(
        classes = {
                SecurityConfiguration.class
})
@WebMvcTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
class MembroResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private MembroResource membroResource;

    @Mock
    private Membro membro;

    @Mock
    private MembroService service;

    @Mock
    private Cidade cidade;

    @DateTimeFormat
    private Date date;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(membroResource).build();

        date = new Date();
    }

    @Test
    @SneakyThrows
    void findById() {
        // Cenario (given)
        BDDMockito.given(service.findById(1)).willReturn(membro.builder()
                .idMembro(1)
                .nome("Fulano")
                .coCargo(1)
                .cidade(cidade.builder().idCidade(1).build())
                .endereco("Endereco")
                .telefone("99999999999")
                .inEstadoCivil("1")
                .dtMembroDesde(date)
                .dtNascimento(date)
                .dtBatismo(date)
                .build());

        // Execucao (when)
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/membro/{id}",1)
                .accept(MediaType.APPLICATION_JSON);

        // verificacao
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void findByIdNotFound() {
        // Cenario (given)
        BDDMockito.given(service.findById(Mockito.anyInt())).willReturn(null);

        // Execucao (when)
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/membro/{id}",1)
                .accept(MediaType.APPLICATION_JSON);

        // verificacao
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    void findAllPage() {
        // Cenario (given)
        BDDMockito.given(service.findById(1)).willReturn(membro.builder()
                .idMembro(1)
                .nome("Fulano")
                .coCargo(1)
                .cidade(cidade.builder().idCidade(1).build())
                .endereco("Endereco")
                .telefone("99999999999")
                .inEstadoCivil("1")
                .dtMembroDesde(date)
                .dtNascimento(date)
                .dtBatismo(date)
                .build());

        // Execucao (when)
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/membro/")
                .accept(MediaType.APPLICATION_JSON);

        // verificacao
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void insertSuccess() {
        String json = mapper.writeValueAsString(membro.builder()
                .nome("Fulano")
                .coCargo(1)
                .cidade(cidade.builder().idCidade(1).build())
                .endereco("Endereco")
                .telefone("99999999999")
                .inEstadoCivil("1")
                .dtMembroDesde(date)
                .dtNascimento(date)
                .dtBatismo(date)
                .build());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/membro")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    void insertErrorsValidator() {
        String json = mapper.writeValueAsString(membro.builder().build());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/membro")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void updateSuccess() {
        String json = mapper.writeValueAsString(membro.builder()
                .nome("Fulano")
                .coCargo(1)
                .cidade(cidade.builder().idCidade(1).build())
                .endereco("Endereco")
                .telefone("99999999999")
                .inEstadoCivil("1")
                .dtMembroDesde(date)
                .dtNascimento(date)
                .dtBatismo(date)
                .build());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/membro/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    void updateErrorsValidator() {
        String json = mapper.writeValueAsString(membro.builder().build());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/membro/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void delete() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/membro/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue( true );
    }
}