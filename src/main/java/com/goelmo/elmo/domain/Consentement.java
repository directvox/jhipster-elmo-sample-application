package com.goelmo.elmo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.goelmo.elmo.domain.enumeration.TypeConsentement;

/**
 * A Consentement.
 */
@Entity
@Table(name = "consentement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Consentement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_consentement", nullable = false)
    private TypeConsentement typeConsentement;

    @NotNull
    @Column(name = "date_consentement", nullable = false)
    private ZonedDateTime dateConsentement;

    @NotNull
    @Column(name = "consentement_recherche", nullable = false)
    private Boolean consentementRecherche;

    
    @Lob
    @Column(name = "consentement_pdf", nullable = false)
    private byte[] consentementPDF;

    @Column(name = "consentement_pdf_content_type", nullable = false)
    private String consentementPDFContentType;

    @Column(name = "date_supprimer_consentement")
    private ZonedDateTime dateSupprimerConsentement;

    @OneToOne
    @JoinColumn(unique = true)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeConsentement getTypeConsentement() {
        return typeConsentement;
    }

    public Consentement typeConsentement(TypeConsentement typeConsentement) {
        this.typeConsentement = typeConsentement;
        return this;
    }

    public void setTypeConsentement(TypeConsentement typeConsentement) {
        this.typeConsentement = typeConsentement;
    }

    public ZonedDateTime getDateConsentement() {
        return dateConsentement;
    }

    public Consentement dateConsentement(ZonedDateTime dateConsentement) {
        this.dateConsentement = dateConsentement;
        return this;
    }

    public void setDateConsentement(ZonedDateTime dateConsentement) {
        this.dateConsentement = dateConsentement;
    }

    public Boolean isConsentementRecherche() {
        return consentementRecherche;
    }

    public Consentement consentementRecherche(Boolean consentementRecherche) {
        this.consentementRecherche = consentementRecherche;
        return this;
    }

    public void setConsentementRecherche(Boolean consentementRecherche) {
        this.consentementRecherche = consentementRecherche;
    }

    public byte[] getConsentementPDF() {
        return consentementPDF;
    }

    public Consentement consentementPDF(byte[] consentementPDF) {
        this.consentementPDF = consentementPDF;
        return this;
    }

    public void setConsentementPDF(byte[] consentementPDF) {
        this.consentementPDF = consentementPDF;
    }

    public String getConsentementPDFContentType() {
        return consentementPDFContentType;
    }

    public Consentement consentementPDFContentType(String consentementPDFContentType) {
        this.consentementPDFContentType = consentementPDFContentType;
        return this;
    }

    public void setConsentementPDFContentType(String consentementPDFContentType) {
        this.consentementPDFContentType = consentementPDFContentType;
    }

    public ZonedDateTime getDateSupprimerConsentement() {
        return dateSupprimerConsentement;
    }

    public Consentement dateSupprimerConsentement(ZonedDateTime dateSupprimerConsentement) {
        this.dateSupprimerConsentement = dateSupprimerConsentement;
        return this;
    }

    public void setDateSupprimerConsentement(ZonedDateTime dateSupprimerConsentement) {
        this.dateSupprimerConsentement = dateSupprimerConsentement;
    }

    public Patient getPatient() {
        return patient;
    }

    public Consentement patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Consentement)) {
            return false;
        }
        return id != null && id.equals(((Consentement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Consentement{" +
            "id=" + getId() +
            ", typeConsentement='" + getTypeConsentement() + "'" +
            ", dateConsentement='" + getDateConsentement() + "'" +
            ", consentementRecherche='" + isConsentementRecherche() + "'" +
            ", consentementPDF='" + getConsentementPDF() + "'" +
            ", consentementPDFContentType='" + getConsentementPDFContentType() + "'" +
            ", dateSupprimerConsentement='" + getDateSupprimerConsentement() + "'" +
            "}";
    }
}
