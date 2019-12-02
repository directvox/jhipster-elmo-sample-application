package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.FormulaireConsentementService;
import com.goelmo.elmo.domain.FormulaireConsentement;
import com.goelmo.elmo.repository.FormulaireConsentementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormulaireConsentement}.
 */
@Service
@Transactional
public class FormulaireConsentementServiceImpl implements FormulaireConsentementService {

    private final Logger log = LoggerFactory.getLogger(FormulaireConsentementServiceImpl.class);

    private final FormulaireConsentementRepository formulaireConsentementRepository;

    public FormulaireConsentementServiceImpl(FormulaireConsentementRepository formulaireConsentementRepository) {
        this.formulaireConsentementRepository = formulaireConsentementRepository;
    }

    /**
     * Save a formulaireConsentement.
     *
     * @param formulaireConsentement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormulaireConsentement save(FormulaireConsentement formulaireConsentement) {
        log.debug("Request to save FormulaireConsentement : {}", formulaireConsentement);
        return formulaireConsentementRepository.save(formulaireConsentement);
    }

    /**
     * Get all the formulaireConsentements.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulaireConsentement> findAll() {
        log.debug("Request to get all FormulaireConsentements");
        return formulaireConsentementRepository.findAll();
    }


    /**
     * Get one formulaireConsentement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormulaireConsentement> findOne(Long id) {
        log.debug("Request to get FormulaireConsentement : {}", id);
        return formulaireConsentementRepository.findById(id);
    }

    /**
     * Delete the formulaireConsentement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormulaireConsentement : {}", id);
        formulaireConsentementRepository.deleteById(id);
    }
}
