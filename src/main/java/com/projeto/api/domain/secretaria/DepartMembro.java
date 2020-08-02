package com.projeto.api.domain.secretaria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "depart_membro", schema = "secretaria")
public class DepartMembro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartMembro;

    @OneToOne()
    @JoinColumn(name = "idDepart", referencedColumnName = "idDepart")
    private Depart depart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMembro")
    @JsonIgnore()
    private Membro membro;
}