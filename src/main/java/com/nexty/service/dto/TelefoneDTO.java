package com.nexty.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.nexty.domain.Telefone} entity.
 */
public class TelefoneDTO implements Serializable {

    private Long id;

    private String ddd;

    private String telefone;

    private Integer tipoTelefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(Integer tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TelefoneDTO)) {
            return false;
        }

        TelefoneDTO telefoneDTO = (TelefoneDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, telefoneDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TelefoneDTO{" +
            "id=" + getId() +
            ", ddd='" + getDdd() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", tipoTelefone=" + getTipoTelefone() +
            "}";
    }
}
