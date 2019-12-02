package com.goelmo.elmo.service;

import com.goelmo.elmo.domain.FormulaireConsentement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormulaireConsentement}.
 */
public interface FormulaireConsentementService {

    /**
     * Save a formulaireConsentement.
     *
     * @param formulaireConsentement the entity to save.
     * @return the persisted entity.
     */
    FormulaireConsentement save(FormulaireConsentement formulaireConsentement);

    /**
     * Get all the formulaireConsentements.
     *
     * @return the list of entities.
     */
    List<FormulaireConsentement> findAll();


    /**
     * Get the "id" formulaireConsentement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormulaireConsentement> findOne(Long id);

    /**
     * Delete the "id" formulaireConsentement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
