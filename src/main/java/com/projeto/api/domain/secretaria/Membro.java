package com.projeto.api.domain.secretaria;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.api.domain.common.Cidade;
import com.projeto.api.service.Validation.MembroInterface;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MembroInterface
@Table(name = "membro", schema = "secretaria")
public class Membro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMembro;

    @NotNull
    @NotEmpty
    @Length(max = 100)
    @Column(name = "stNome")
    private String nome;

    @Length(max = 100)
    @Column(name = "stNaturalidade")
    private String naturalidade;

    @Column(name = "nuCpf")
    @Length(max = 11)
    private String cpf;

    @Length(max = 100)
    @Column(name = "stPai")
    private String pai;

    @Length(max = 100)
    @Column(name = "stMae")
    private String mae;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "dtNascimento")
    private Date dtNascimento;

    @NotNull
    @Column(name = "inEstadoCivil")
    private String inEstadoCivil;

    @Column(name = "nuIdentidade")
    private String identidade;

    @NotNull
    @Length(max = 20)
    @Column(name = "nuTelefone")
    private String telefone;

    @Length(max = 20)
    @Column(name = "nuTelefone_2")
    private String telefone2;


    @NotNull
    @Length(max = 100)
    @Column(name = "stEndereco")
    private String endereco;

    @Column(name = "nuCep")
    private String  cep;

    @NotNull
    @OneToOne()
    @JoinColumn(name = "idCidade", referencedColumnName = "id")
    private Cidade cidade;

    @NotNull
    @Column(name = "coCargo")
    private Integer coCargo;

    @Column(name = "stUrlImg")
    private String foto;

    @OneToOne()
    @JoinColumn(name = "idIgreja", referencedColumnName = "idIgreja")
    private Igreja igreja;

    @Length(max = 20)
    @Column(name = "stOrgaoIdentidade")
    private String orgaoIdentidade;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "dtBatismo")
    private Date dtBatismo;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "dtMembroDesde")
    private Date dtMembroDesde;

    @Column(name = "inDepartLider")
    private Integer inDepartLider;

    @Transient
    private String CodigoBase64;

    @Transient
    private String nameFileBase64;

    @OneToMany(mappedBy="membro", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartMembro> departMembros = new ArrayList<>();

//    public void setCpf(String cpf) {
//        this.cpf = cpf.replaceAll("[.-]", "");
//    }
}