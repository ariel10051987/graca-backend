package com.projeto.api.domain.secretaria;

import com.projeto.api.service.Validation.MembroInterface;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MembroInterface
@Entity
@Table(name = "depart", schema = "secretaria")
public class Depart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepart;

    @Column(name = "stNome")
    private String nome;
}