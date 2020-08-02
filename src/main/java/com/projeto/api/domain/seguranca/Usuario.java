package com.projeto.api.domain.seguranca;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import com.projeto.api.service.Validation.UsuarioInterface;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Entity
@UsuarioInterface
@Table(name = "usuario", schema = "seguranca")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @OneToOne()
    @JoinColumn(name = "idPerfil", referencedColumnName = "idPerfil")
    private Perfil perfil;

    @NotNull
    @NotEmpty
    @Length(max = 100)
    @Column(name = "dcNome")
    private String nome;

    @Column(name = "nuCpf", nullable = true)
    @Length(max = 11)
    private String cpf;

    @Email
    @NotNull
    @NotEmpty
    @Length(max = 100)
    @Column(name = "dcEmail", unique = true, nullable = true)
    private String email;

    @NotNull
    @NotEmpty
    @Length(max = 100)
    @Column(name = "dcPassword")
    private String senha;

    @Length(max = 11)
    @Column(name = "nuTelefone")
    private String telefone;

    @Length(max = 11)
    @Column(name = "nuCelular")
    private String celular;

    @Transient
    private String base64Imagem;

    @Transient
    private String nameBase64Imagem;

    @Length(max = 100)
    @Column(name = "stUrlAssinatura")
    private String urlAssinatura;

    private String inAtivo;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dtInclusao;
}