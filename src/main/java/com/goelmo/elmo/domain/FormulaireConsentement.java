package com.goelmo.elmo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A FormulaireConsentement.
 */
@Entity
@Table(name = "formulaire_consentement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FormulaireConsentement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_formulaire")
    private String idFormulaire;

    @Column(name = "nom_formulaire")
    private String nomFormulaire;

    @Column(name = "date_formulaire")
    private String dateFormulaire;

    @Column(name = "actif")
    private Boolean actif;

    @Lob
    @Column(name = "formulaire_consentement_texte")
    private String formulaireConsentementTexte;

    @Lob
    @Column(name = "formulaire_consentement_pdf")
    private byte[] formulaireConsentementPDF;

    @Column(name = "formulaire_consentement_pdf_content_type")
    private String formulaireConsentementPDFContentType;

    @Column(name = "formulaire_consentement_url")
    private String formulaireConsentementURL;

    @OneToOne
    @JoinColumn(unique = true)
    private Consentement consentement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdFormulaire() {
        return idFormulaire;
    }

    public FormulaireConsentement idFormulaire(String idFormulaire) {
        this.idFormulaire = idFormulaire;
        return this;
    }

    public void setIdFormulaire(String idFormulaire) {
        this.idFormulaire = idFormulaire;
    }

    public String getNomFormulaire() {
        return nomFormulaire;
    }

    public FormulaireConsentement nomFormulaire(String nomFormulaire) {
        this.nomFormulaire = nomFormulaire;
        return this;
    }

    public void setNomFormulaire(String nomFormulaire) {
        this.nomFormulaire = nomFormulaire;
    }

    public String getDateFormulaire() {
        return dateFormulaire;
    }

    public FormulaireConsentement dateFormulaire(String dateFormulaire) {
        this.dateFormulaire = dateFormulaire;
        return this;
    }

    public void setDateFormulaire(String dateFormulaire) {
        this.dateFormulaire = dateFormulaire;
    }

    public Boolean isActif() {
        return actif;
    }

    public FormulaireConsentement actif(Boolean actif) {
        this.actif = actif;
        return this;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getFormulaireConsentementTexte() {
        return formulaireConsentementTexte;
    }

    public FormulaireConsentement formulaireConsentementTexte(String formulaireConsentementTexte) {
        this.formulaireConsentementTexte = formulaireConsentementTexte;
        return this;
    }

    public void setFormulaireConsentementTexte(String formulaireConsentementTexte) {
        this.formulaireConsentementTexte = formulaireConsentementTexte;
    }

    public byte[] getFormulaireConsentementPDF() {
        return formulaireConsentementPDF;
    }

    public FormulaireConsentement formulaireConsentementPDF(byte[] formulaireConsentementPDF) {
        this.formulaireConsentementPDF = formulaireConsentementPDF;
        return this;
    }

    public void setFormulaireConsentementPDF(byte[] formulaireConsentementPDF) {
        this.formulaireConsentementPDF = formulaireConsentementPDF;
    }

    public String getFormulaireConsentementPDFContentType() {
        return formulaireConsentementPDFContentType;
    }

    public FormulaireConsentement formulaireConsentementPDFContentType(String formulaireConsentementPDFContentType) {
        this.formulaireConsentementPDFContentType = formulaireConsentementPDFContentType;
        return this;
    }

    public void setFormulaireConsentementPDFContentType(String formulaireConsentementPDFContentType) {
        this.formulaireConsentementPDFContentType = formulaireConsentementPDFContentType;
    }

    public String getFormulaireConsentementURL() {
        return formulaireConsentementURL;
    }

    public FormulaireConsentement formulaireConsentementURL(String formulaireConsentementURL) {
        this.formulaireConsentementURL = formulaireConsentementURL;
        return this;
    }

    public void setFormulaireConsentementURL(String formulaireConsentementURL) {
        this.formulaireConsentementURL = formulaireConsentementURL;
    }

    public Consentement getConsentement() {
        return consentement;
    }

    public FormulaireConsentement consentement(Consentement consentement) {
        this.consentement = consentement;
        return this;
    }

    public void setConsentement(Consentement consentement) {
        this.consentement = consentement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormulaireConsentement)) {
            return false;
        }
        return id != null && id.equals(((FormulaireConsentement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FormulaireConsentement{" +
            "id=" + getId() +
            ", idFormulaire='" + getIdFormulaire() + "'" +
            ", nomFormulaire='" + getNomFormulaire() + "'" +
            ", dateFormulaire='" + getDateFormulaire() + "'" +
            ", actif='" + isActif() + "'" +
            ", formulaireConsentementTexte='" + getFormulaireConsentementTexte() + "'" +
            ", formulaireConsentementPDF='" + getFormulaireConsentementPDF() + "'" +
            ", formulaireConsentementPDFContentType='" + getFormulaireConsentementPDFContentType() + "'" +
            ", formulaireConsentementURL='" + getFormulaireConsentementURL() + "'" +
            "}";
    }
}
