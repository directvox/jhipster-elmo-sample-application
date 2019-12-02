package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.DemandeDeServiceService;
import com.goelmo.elmo.domain.DemandeDeService;
import com.goelmo.elmo.repository.DemandeDeServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DemandeDeService}.
 */
@Service
@Transactional
public class DemandeDeServiceServiceImpl implements DemandeDeServiceService {

    private final Logger log = LoggerFactory.getLogger(DemandeDeServiceServiceImpl.class);

    private final DemandeDeServiceRepository demandeDeServiceRepository;

    public DemandeDeServiceServiceImpl(DemandeDeServiceRepository demandeDeServiceRepository) {
        this.demandeDeServiceRepository = demandeDeServiceRepository;
    }

    /**
     * Save a demandeDeService.
     *
     * @param demandeDeService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DemandeDeService save(DemandeDeService demandeDeService) {
        log.debug("Request to save DemandeDeService : {}", demandeDeService);
        return demandeDeServiceRepository.save(demandeDeService);
    }

    /**
     * Get all the demandeDeServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemandeDeService> findAll(Pageable pageable) {
        log.debug("Request to get all DemandeDeServices");
        return demandeDeServiceRepository.findAll(pageable);
    }

    /**
     * Get all the demandeDeServices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DemandeDeService> findAllWithEagerRelationships(Pageable pageable) {
        return demandeDeServiceRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one demandeDeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemandeDeService> findOne(Long id) {
        log.debug("Request to get DemandeDeService : {}", id);
        return demandeDeServiceRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the demandeDeService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DemandeDeService : {}", id);
        demandeDeServiceRepository.deleteById(id);
    }
}
