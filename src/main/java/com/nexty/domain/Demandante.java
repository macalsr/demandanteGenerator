package com.nexty.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nexty.domain.enumeration.Sexo;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "demandante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Demandante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nm_demandante", nullable = false)
    private String nmDemandante;

    @NotNull
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "cd_tipo_demandante")
    private Integer cdTipoDemandante;

    @Column(name = "cd_atendimento")
    private Integer cdAtendimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "active")
    private Boolean active;

    @JsonIgnoreProperties(value = { "demandante" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Telefone telefone;

    @JsonIgnoreProperties(value = { "demandante" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Endereco endereco;

    public Long getId() {
        return this.id;
    }

    public Demandante id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmDemandante() {
        return this.nmDemandante;
    }

    public Demandante nmDemandante(String nmDemandante) {
        this.setNmDemandante(nmDemandante);
        return this;
    }

    public void setNmDemandante(String nmDemandante) {
        this.nmDemandante = nmDemandante;
    }

    public String getCpf() {
        return this.cpf;
    }

    public Demandante cpf(String cpf) {
        this.setCpf(cpf);
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCdTipoDemandante() {
        return this.cdTipoDemandante;
    }

    public Demandante cdTipoDemandante(Integer cdTipoDemandante) {
        this.setCdTipoDemandante(cdTipoDemandante);
        return this;
    }

    public void setCdTipoDemandante(Integer cdTipoDemandante) {
        this.cdTipoDemandante = cdTipoDemandante;
    }

    public Integer getCdAtendimento() {
        return this.cdAtendimento;
    }

    public Demandante cdAtendimento(Integer cdAtendimento) {
        this.setCdAtendimento(cdAtendimento);
        return this;
    }

    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public Demandante sexo(Sexo sexo) {
        this.setSexo(sexo);
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Demandante active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Telefone getTelefone() {
        return this.telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Demandante telefone(Telefone telefone) {
        this.setTelefone(telefone);
        return this;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Demandante endereco(Endereco endereco) {
        this.setEndereco(endereco);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demandante)) {
            return false;
        }
        return id != null && id.equals(((Demandante) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demandante{" +
            "id=" + getId() +
            ", nmDemandante='" + getNmDemandante() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", cdTipoDemandante=" + getCdTipoDemandante() +
            ", cdAtendimento=" + getCdAtendimento() +
            ", sexo='" + getSexo() + "'" +
            ", active='" + getActive() + "'" +
            "}";
    }
}
