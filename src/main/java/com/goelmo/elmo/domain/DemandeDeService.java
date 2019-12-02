package com.goelmo.elmo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.goelmo.elmo.domain.enumeration.StatutDemande;

import com.goelmo.elmo.domain.enumeration.Language;

import com.goelmo.elmo.domain.enumeration.Priorite;

import com.goelmo.elmo.domain.enumeration.Modalite;

import com.goelmo.elmo.domain.enumeration.TypeActivite;

/**
 * A DemandeDeService.
 */
@Entity
@Table(name = "demande_de_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemandeDeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutDemande statut;

    @NotNull
    @Column(name = "date_creation_demande", nullable = false)
    private ZonedDateTime dateCreationDemande;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lanque", nullable = false)
    private Language lanque;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priorite", nullable = false)
    private Priorite priorite;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "modalite", nullable = false)
    private Modalite modalite;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_activite", nullable = false)
    private TypeActivite typeActivite;

    @OneToOne
    @JoinColumn(unique = true)
    private Patient patient;

    @OneToOne
    @JoinColumn(unique = true)
    private Destinataire destinataire;

    @OneToOne
    @JoinColumn(unique = true)
    private SpecClinique specialiteClinique;

    @ManyToOne
    @JsonIgnoreProperties("demandeDeServices")
    private DemandeDeService ensembre;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "demande_de_service_formulaire_evaluation",
               joinColumns = @JoinColumn(name = "demande_de_service_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "formulaire_evaluation_id", referencedColumnName = "id"))
    private Set<FormulaireEvaluation> formulaireEvaluations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public DemandeDeService statut(StatutDemande statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public ZonedDateTime getDateCreationDemande() {
        return dateCreationDemande;
    }

    public DemandeDeService dateCreationDemande(ZonedDateTime dateCreationDemande) {
        this.dateCreationDemande = dateCreationDemande;
        return this;
    }

    public void setDateCreationDemande(ZonedDateTime dateCreationDemande) {
        this.dateCreationDemande = dateCreationDemande;
    }

    public Language getLanque() {
        return lanque;
    }

    public DemandeDeService lanque(Language lanque) {
        this.lanque = lanque;
        return this;
    }

    public void setLanque(Language lanque) {
        this.lanque = lanque;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public DemandeDeService priorite(Priorite priorite) {
        this.priorite = priorite;
        return this;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Modalite getModalite() {
        return modalite;
    }

    public DemandeDeService modalite(Modalite modalite) {
        this.modalite = modalite;
        return this;
    }

    public void setModalite(Modalite modalite) {
        this.modalite = modalite;
    }

    public TypeActivite getTypeActivite() {
        return typeActivite;
    }

    public DemandeDeService typeActivite(TypeActivite typeActivite) {
        this.typeActivite = typeActivite;
        return this;
    }

    public void setTypeActivite(TypeActivite typeActivite) {
        this.typeActivite = typeActivite;
    }

    public Patient getPatient() {
        return patient;
    }

    public DemandeDeService patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Destinataire getDestinataire() {
        return destinataire;
    }

    public DemandeDeService destinataire(Destinataire destinataire) {
        this.destinataire = destinataire;
        return this;
    }

    public void setDestinataire(Destinataire destinataire) {
        this.destinataire = destinataire;
    }

    public SpecClinique getSpecialiteClinique() {
        return specialiteClinique;
    }

    public DemandeDeService specialiteClinique(SpecClinique specClinique) {
        this.specialiteClinique = specClinique;
        return this;
    }

    public void setSpecialiteClinique(SpecClinique specClinique) {
        this.specialiteClinique = specClinique;
    }

    public DemandeDeService getEnsembre() {
        return ensembre;
    }

    public DemandeDeService ensembre(DemandeDeService demandeDeService) {
        this.ensembre = demandeDeService;
        return this;
    }

    public void setEnsembre(DemandeDeService demandeDeService) {
        this.ensembre = demandeDeService;
    }

    public Set<FormulaireEvaluation> getFormulaireEvaluations() {
        return formulaireEvaluations;
    }

    public DemandeDeService formulaireEvaluations(Set<FormulaireEvaluation> formulaireEvaluations) {
        this.formulaireEvaluations = formulaireEvaluations;
        return this;
    }

    public DemandeDeService addFormulaireEvaluation(FormulaireEvaluation formulaireEvaluation) {
        this.formulaireEvaluations.add(formulaireEvaluation);
        formulaireEvaluation.getDemandeDeServices().add(this);
        return this;
    }

    public DemandeDeService removeFormulaireEvaluation(FormulaireEvaluation formulaireEvaluation) {
        this.formulaireEvaluations.remove(formulaireEvaluation);
        formulaireEvaluation.getDemandeDeServices().remove(this);
        return this;
    }

    public void setFormulaireEvaluations(Set<FormulaireEvaluation> formulaireEvaluations) {
        this.formulaireEvaluations = formulaireEvaluations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeDeService)) {
            return false;
        }
        return id != null && id.equals(((DemandeDeService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DemandeDeService{" +
            "id=" + getId() +
            ", statut='" + getStatut() + "'" +
            ", dateCreationDemande='" + getDateCreationDemande() + "'" +
            ", lanque='" + getLanque() + "'" +
            ", priorite='" + getPriorite() + "'" +
            ", modalite='" + getModalite() + "'" +
            ", typeActivite='" + getTypeActivite() + "'" +
            "}";
    }
}
