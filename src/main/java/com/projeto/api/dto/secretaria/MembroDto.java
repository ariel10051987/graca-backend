package com.projeto.api.dto.secretaria;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.api.domain.common.Cidade;
import com.projeto.api.domain.common.Estado;
import com.projeto.api.domain.secretaria.DepartMembro;
import com.projeto.api.domain.secretaria.Igreja;
import com.projeto.api.domain.secretaria.Membro;
import com.projeto.api.service.Format.FormatString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MembroDto implements Serializable {

    private Integer idMembro;

    private String nome;

    private String naturalidade;

    private String cpf;

    private String pai;

    private String mae;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtNascimento;

    private String inEstadoCivil;

    private String identidade;

    private String telefone;

    private String telefone2;

    private String endereco;

    private String  cep;

    private Cidade cidade;

    private Estado estado;

    private Integer coCargo;

    private String foto;

    private Igreja igreja;

    private String orgaoIdentidade;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtBatismo;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtMembroDesde;

    private Integer inDepartLider;

    private List<DepartMembro> departMembros = new ArrayList<>();

    public MembroDto() {

    }

    public MembroDto(Membro obj) {
        this.idMembro = obj.getIdMembro();
        this.nome = obj.getNome();
        this.naturalidade = obj.getNaturalidade();
        this.cpf = obj.getCpf();
        this.pai = obj.getPai();
        this.mae = obj.getMae();
        this.dtNascimento = obj.getDtNascimento();
        this.inEstadoCivil = obj.getInEstadoCivil();
        this.identidade = obj.getIdentidade();
        this.telefone = obj.getTelefone();
        this.telefone2 = obj.getTelefone2();
        this.endereco = obj.getEndereco();
        this.cep = obj.getCep();
        this.cidade = obj.getCidade();
        this.estado = obj.getCidade().getEstado();
        this.coCargo = obj.getCoCargo();
        this.foto = obj.getFoto();
        this.igreja = obj.getIgreja();
        this.orgaoIdentidade = obj.getOrgaoIdentidade();
        this.dtBatismo = obj.getDtBatismo();
        this.dtMembroDesde = obj.getDtMembroDesde();
        this.inDepartLider = obj.getInDepartLider();
        this.departMembros = obj.getDepartMembros();
    }

    public Integer getIdMembro() {
        return idMembro;
    }

    public String getNome() {
        return nome;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getCpf() {
        return new FormatString().format(cpf, "###.###.###-##");
    }

    public String getPai() {
        return pai;
    }

    public String getMae() {
        return mae;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public String getInEstadoCivil() {
        return inEstadoCivil;
    }

    public String getIdentidade() {
        return identidade;
    }

    public String getTelefone() {
        return new FormatString().format(telefone, "(##) #####-####");
    }

    public String getTelefone2() {
        return new FormatString().format(telefone2, "(##) #####-####");
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Integer getCoCargo() {
        return coCargo;
    }

    public String getFoto() {
        return foto;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public String getOrgaoIdentidade() {
        return orgaoIdentidade;
    }

    public Date getDtBatismo() {
        return dtBatismo;
    }

    public Date getDtMembroDesde() {
        return dtMembroDesde;
    }

    public Integer getInDepartLider() {
        return inDepartLider;
    }

    public List<DepartMembro> getDepartMembros() {
        return departMembros;
    }
}
