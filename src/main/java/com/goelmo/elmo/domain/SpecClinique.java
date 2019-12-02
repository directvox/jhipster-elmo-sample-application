package com.goelmo.elmo.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A SpecClinique.
 */
@Entity
@Table(name = "spec_clinique")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SpecClinique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_specialite_clinique", nullable = false)
    private String nomSpecialiteClinique;

    @ManyToOne
    @JsonIgnoreProperties("specCliniques")
    private FormulaireEvaluation specialite;

    @ManyToMany(mappedBy = "specCliniques")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Destinataire> destinataires = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomSpecialiteClinique() {
        return nomSpecialiteClinique;
    }

    public SpecClinique nomSpecialiteClinique(String nomSpecialiteClinique) {
        this.nomSpecialiteClinique = nomSpecialiteClinique;
        return this;
    }

    public void setNomSpecialiteClinique(String nomSpecialiteClinique) {
        this.nomSpecialiteClinique = nomSpecialiteClinique;
    }

    public FormulaireEvaluation getSpecialite() {
        return specialite;
    }

    public SpecClinique specialite(FormulaireEvaluation formulaireEvaluation) {
        this.specialite = formulaireEvaluation;
        return this;
    }

    public void setSpecialite(FormulaireEvaluation formulaireEvaluation) {
        this.specialite = formulaireEvaluation;
    }

    public Set<Destinataire> getDestinataires() {
        return destinataires;
    }

    public SpecClinique destinataires(Set<Destinataire> destinataires) {
        this.destinataires = destinataires;
        return this;
    }

    public SpecClinique addDestinataire(Destinataire destinataire) {
        this.destinataires.add(destinataire);
        destinataire.getSpecCliniques().add(this);
        return this;
    }

    public SpecClinique removeDestinataire(Destinataire destinataire) {
        this.destinataires.remove(destinataire);
        destinataire.getSpecCliniques().remove(this);
        return this;
    }

    public void setDestinataires(Set<Destinataire> destinataires) {
        this.destinataires = destinataires;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecClinique)) {
            return false;
        }
        return id != null && id.equals(((SpecClinique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SpecClinique{" +
            "id=" + getId() +
            ", nomSpecialiteClinique='" + getNomSpecialiteClinique() + "'" +
            "}";
    }
}
