package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.IntervenantService;
import com.goelmo.elmo.domain.Intervenant;
import com.goelmo.elmo.repository.IntervenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Intervenant}.
 */
@Service
@Transactional
public class IntervenantServiceImpl implements IntervenantService {

    private final Logger log = LoggerFactory.getLogger(IntervenantServiceImpl.class);

    private final IntervenantRepository intervenantRepository;

    public IntervenantServiceImpl(IntervenantRepository intervenantRepository) {
        this.intervenantRepository = intervenantRepository;
    }

    /**
     * Save a intervenant.
     *
     * @param intervenant the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Intervenant save(Intervenant intervenant) {
        log.debug("Request to save Intervenant : {}", intervenant);
        return intervenantRepository.save(intervenant);
    }

    /**
     * Get all the intervenants.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Intervenant> findAll() {
        log.debug("Request to get all Intervenants");
        return intervenantRepository.findAll();
    }


    /**
     * Get one intervenant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Intervenant> findOne(Long id) {
        log.debug("Request to get Intervenant : {}", id);
        return intervenantRepository.findById(id);
    }

    /**
     * Delete the intervenant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Intervenant : {}", id);
        intervenantRepository.deleteById(id);
    }
}
