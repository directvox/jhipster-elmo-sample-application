package com.goelmo.elmo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Destinataire.
 */
@Entity
@Table(name = "destinataire")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Destinataire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_destinataire")
    private String nomDestinataire;

    @ManyToOne
    @JsonIgnoreProperties("destinataires")
    private Intervenant ensembreDestinataire;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "destinataire_spec_clinique",
               joinColumns = @JoinColumn(name = "destinataire_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "spec_clinique_id", referencedColumnName = "id"))
    private Set<SpecClinique> specCliniques = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDestinataire() {
        return nomDestinataire;
    }

    public Destinataire nomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
        return this;
    }

    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

    public Intervenant getEnsembreDestinataire() {
        return ensembreDestinataire;
    }

    public Destinataire ensembreDestinataire(Intervenant intervenant) {
        this.ensembreDestinataire = intervenant;
        return this;
    }

    public void setEnsembreDestinataire(Intervenant intervenant) {
        this.ensembreDestinataire = intervenant;
    }

    public Set<SpecClinique> getSpecCliniques() {
        return specCliniques;
    }

    public Destinataire specCliniques(Set<SpecClinique> specCliniques) {
        this.specCliniques = specCliniques;
        return this;
    }

    public Destinataire addSpecClinique(SpecClinique specClinique) {
        this.specCliniques.add(specClinique);
        specClinique.getDestinataires().add(this);
        return this;
    }

    public Destinataire removeSpecClinique(SpecClinique specClinique) {
        this.specCliniques.remove(specClinique);
        specClinique.getDestinataires().remove(this);
        return this;
    }

    public void setSpecCliniques(Set<SpecClinique> specCliniques) {
        this.specCliniques = specCliniques;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Destinataire)) {
            return false;
        }
        return id != null && id.equals(((Destinataire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Destinataire{" +
            "id=" + getId() +
            ", nomDestinataire='" + getNomDestinataire() + "'" +
            "}";
    }
}
