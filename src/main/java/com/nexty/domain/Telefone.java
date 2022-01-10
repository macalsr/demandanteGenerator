package com.nexty.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "telefone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ddd")
    private String ddd;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "tipo_telefone")
    private Integer tipoTelefone;

    @JsonIgnoreProperties(value = { "telefone", "endereco" }, allowSetters = true)
    @OneToOne(mappedBy = "telefone")
    private Demandante demandante;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Telefone id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return this.ddd;
    }

    public Telefone ddd(String ddd) {
        this.setDdd(ddd);
        return this;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Telefone telefone(String telefone) {
        this.setTelefone(telefone);
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getTipoTelefone() {
        return this.tipoTelefone;
    }

    public Telefone tipoTelefone(Integer tipoTelefone) {
        this.setTipoTelefone(tipoTelefone);
        return this;
    }

    public void setTipoTelefone(Integer tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Demandante getDemandante() {
        return this.demandante;
    }

    public void setDemandante(Demandante demandante) {
        if (this.demandante != null) {
            this.demandante.setTelefone(null);
        }
        if (demandante != null) {
            demandante.setTelefone(this);
        }
        this.demandante = demandante;
    }

    public Telefone demandante(Demandante demandante) {
        this.setDemandante(demandante);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Telefone)) {
            return false;
        }
        return id != null && id.equals(((Telefone) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Telefone{" +
            "id=" + getId() +
            ", ddd='" + getDdd() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", tipoTelefone=" + getTipoTelefone() +
            "}";
    }
}
