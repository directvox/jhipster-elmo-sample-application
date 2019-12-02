package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.FormulaireEvaluationService;
import com.goelmo.elmo.domain.FormulaireEvaluation;
import com.goelmo.elmo.repository.FormulaireEvaluationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormulaireEvaluation}.
 */
@Service
@Transactional
public class FormulaireEvaluationServiceImpl implements FormulaireEvaluationService {

    private final Logger log = LoggerFactory.getLogger(FormulaireEvaluationServiceImpl.class);

    private final FormulaireEvaluationRepository formulaireEvaluationRepository;

    public FormulaireEvaluationServiceImpl(FormulaireEvaluationRepository formulaireEvaluationRepository) {
        this.formulaireEvaluationRepository = formulaireEvaluationRepository;
    }

    /**
     * Save a formulaireEvaluation.
     *
     * @param formulaireEvaluation the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormulaireEvaluation save(FormulaireEvaluation formulaireEvaluation) {
        log.debug("Request to save FormulaireEvaluation : {}", formulaireEvaluation);
        return formulaireEvaluationRepository.save(formulaireEvaluation);
    }

    /**
     * Get all the formulaireEvaluations.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulaireEvaluation> findAll() {
        log.debug("Request to get all FormulaireEvaluations");
        return formulaireEvaluationRepository.findAll();
    }


    /**
     * Get one formulaireEvaluation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormulaireEvaluation> findOne(Long id) {
        log.debug("Request to get FormulaireEvaluation : {}", id);
        return formulaireEvaluationRepository.findById(id);
    }

    /**
     * Delete the formulaireEvaluation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormulaireEvaluation : {}", id);
        formulaireEvaluationRepository.deleteById(id);
    }
}
