package com.projeto.api.domain.common;

import com.projeto.api.domain.secretaria.DepartMembro;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "estados", schema = "public")
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sigla")
    private String sigla;

    @OneToMany(mappedBy="estado", fetch = FetchType.EAGER)
    private List<Cidade> cidades = new ArrayList<>();
}