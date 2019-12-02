package com.goelmo.elmo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AttachementDemandeDeService.
 */
@Entity
@Table(name = "attachement_demande_de_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttachementDemandeDeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Lob
    @Column(name = "fichier")
    private byte[] fichier;

    @Column(name = "fichier_content_type")
    private String fichierContentType;

    @Column(name = "ref_externe")
    private String refExterne;

    @Lob
    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(unique = true)
    private DemandeDeService ref;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public AttachementDemandeDeService nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public AttachementDemandeDeService fichier(byte[] fichier) {
        this.fichier = fichier;
        return this;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public String getFichierContentType() {
        return fichierContentType;
    }

    public AttachementDemandeDeService fichierContentType(String fichierContentType) {
        this.fichierContentType = fichierContentType;
        return this;
    }

    public void setFichierContentType(String fichierContentType) {
        this.fichierContentType = fichierContentType;
    }

    public String getRefExterne() {
        return refExterne;
    }

    public AttachementDemandeDeService refExterne(String refExterne) {
        this.refExterne = refExterne;
        return this;
    }

    public void setRefExterne(String refExterne) {
        this.refExterne = refExterne;
    }

    public String getNote() {
        return note;
    }

    public AttachementDemandeDeService note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public DemandeDeService getRef() {
        return ref;
    }

    public AttachementDemandeDeService ref(DemandeDeService demandeDeService) {
        this.ref = demandeDeService;
        return this;
    }

    public void setRef(DemandeDeService demandeDeService) {
        this.ref = demandeDeService;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttachementDemandeDeService)) {
            return false;
        }
        return id != null && id.equals(((AttachementDemandeDeService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AttachementDemandeDeService{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", fichier='" + getFichier() + "'" +
            ", fichierContentType='" + getFichierContentType() + "'" +
            ", refExterne='" + getRefExterne() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
