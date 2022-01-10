package com.nexty.service.dto;

import com.nexty.domain.enumeration.Sexo;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nexty.domain.Demandante} entity.
 */
public class DemandanteDTO implements Serializable {

    private Long id;

    @NotNull
    private String nmDemandante;

    @NotNull
    private String cpf;

    private Integer cdTipoDemandante;

    private Integer cdAtendimento;

    private Sexo sexo;

    private Boolean active;

    private TelefoneDTO telefone;

    private EnderecoDTO endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmDemandante() {
        return nmDemandante;
    }

    public void setNmDemandante(String nmDemandante) {
        this.nmDemandante = nmDemandante;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCdTipoDemandante() {
        return cdTipoDemandante;
    }

    public void setCdTipoDemandante(Integer cdTipoDemandante) {
        this.cdTipoDemandante = cdTipoDemandante;
    }

    public Integer getCdAtendimento() {
        return cdAtendimento;
    }

    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public TelefoneDTO getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneDTO telefone) {
        this.telefone = telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandanteDTO)) {
            return false;
        }

        DemandanteDTO demandanteDTO = (DemandanteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, demandanteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandanteDTO{" +
            "id=" + getId() +
            ", nmDemandante='" + getNmDemandante() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", cdTipoDemandante=" + getCdTipoDemandante() +
            ", cdAtendimento=" + getCdAtendimento() +
            ", sexo='" + getSexo() + "'" +
            ", active='" + getActive() + "'" +
            ", telefone=" + getTelefone() +
            ", endereco=" + getEndereco() +
            "}";
    }
}
