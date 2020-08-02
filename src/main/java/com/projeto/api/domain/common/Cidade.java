package com.projeto.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.api.domain.secretaria.Igreja;
import com.projeto.api.service.Validation.UsuarioInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@UsuarioInterface
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidades", schema = "public")
public class Cidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCidade;

    @Column(name = "nome")
    private String nome;

    @Column(name = "codigoIbge")
    private Integer coIbge;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoId", referencedColumnName = "id")
    private Estado estado;
}