package com.goelmo.elmo.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FormulaireEvaluation.
 */
@Entity
@Table(name = "formulaire_evaluation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FormulaireEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_formulaire")
    private String typeFormulaire;

    @ManyToMany(mappedBy = "formulaireEvaluations")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<DemandeDeService> demandeDeServices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeFormulaire() {
        return typeFormulaire;
    }

    public FormulaireEvaluation typeFormulaire(String typeFormulaire) {
        this.typeFormulaire = typeFormulaire;
        return this;
    }

    public void setTypeFormulaire(String typeFormulaire) {
        this.typeFormulaire = typeFormulaire;
    }

    public Set<DemandeDeService> getDemandeDeServices() {
        return demandeDeServices;
    }

    public FormulaireEvaluation demandeDeServices(Set<DemandeDeService> demandeDeServices) {
        this.demandeDeServices = demandeDeServices;
        return this;
    }

    public FormulaireEvaluation addDemandeDeService(DemandeDeService demandeDeService) {
        this.demandeDeServices.add(demandeDeService);
        demandeDeService.getFormulaireEvaluations().add(this);
        return this;
    }

    public FormulaireEvaluation removeDemandeDeService(DemandeDeService demandeDeService) {
        this.demandeDeServices.remove(demandeDeService);
        demandeDeService.getFormulaireEvaluations().remove(this);
        return this;
    }

    public void setDemandeDeServices(Set<DemandeDeService> demandeDeServices) {
        this.demandeDeServices = demandeDeServices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormulaireEvaluation)) {
            return false;
        }
        return id != null && id.equals(((FormulaireEvaluation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FormulaireEvaluation{" +
            "id=" + getId() +
            ", typeFormulaire='" + getTypeFormulaire() + "'" +
            "}";
    }
}
