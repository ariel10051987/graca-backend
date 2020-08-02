package com.projeto.api.domain.seguranca;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="perfil", schema="seguranca")
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;

    @Column(name = "dcNome")
    private String dcPerfil;

    private String stTitulo;
}
