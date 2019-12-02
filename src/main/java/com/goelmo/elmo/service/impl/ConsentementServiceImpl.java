package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.ConsentementService;
import com.goelmo.elmo.domain.Consentement;
import com.goelmo.elmo.repository.ConsentementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Consentement}.
 */
@Service
@Transactional
public class ConsentementServiceImpl implements ConsentementService {

    private final Logger log = LoggerFactory.getLogger(ConsentementServiceImpl.class);

    private final ConsentementRepository consentementRepository;

    public ConsentementServiceImpl(ConsentementRepository consentementRepository) {
        this.consentementRepository = consentementRepository;
    }

    /**
     * Save a consentement.
     *
     * @param consentement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Consentement save(Consentement consentement) {
        log.debug("Request to save Consentement : {}", consentement);
        return consentementRepository.save(consentement);
    }

    /**
     * Get all the consentements.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Consentement> findAll() {
        log.debug("Request to get all Consentements");
        return consentementRepository.findAll();
    }


    /**
     * Get one consentement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Consentement> findOne(Long id) {
        log.debug("Request to get Consentement : {}", id);
        return consentementRepository.findById(id);
    }

    /**
     * Delete the consentement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Consentement : {}", id);
        consentementRepository.deleteById(id);
    }
}
