package com.goelmo.elmo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ReponseDemandeDeService.
 */
@Entity
@Table(name = "reponse_demande_de_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReponseDemandeDeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_reponse", nullable = false)
    private ZonedDateTime dateReponse;

    @OneToOne
    @JoinColumn(unique = true)
    private DemandeDeService demandeDeService;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateReponse() {
        return dateReponse;
    }

    public ReponseDemandeDeService dateReponse(ZonedDateTime dateReponse) {
        this.dateReponse = dateReponse;
        return this;
    }

    public void setDateReponse(ZonedDateTime dateReponse) {
        this.dateReponse = dateReponse;
    }

    public DemandeDeService getDemandeDeService() {
        return demandeDeService;
    }

    public ReponseDemandeDeService demandeDeService(DemandeDeService demandeDeService) {
        this.demandeDeService = demandeDeService;
        return this;
    }

    public void setDemandeDeService(DemandeDeService demandeDeService) {
        this.demandeDeService = demandeDeService;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReponseDemandeDeService)) {
            return false;
        }
        return id != null && id.equals(((ReponseDemandeDeService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReponseDemandeDeService{" +
            "id=" + getId() +
            ", dateReponse='" + getDateReponse() + "'" +
            "}";
    }
}
