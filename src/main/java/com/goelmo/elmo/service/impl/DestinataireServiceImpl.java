package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.DestinataireService;
import com.goelmo.elmo.domain.Destinataire;
import com.goelmo.elmo.repository.DestinataireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Destinataire}.
 */
@Service
@Transactional
public class DestinataireServiceImpl implements DestinataireService {

    private final Logger log = LoggerFactory.getLogger(DestinataireServiceImpl.class);

    private final DestinataireRepository destinataireRepository;

    public DestinataireServiceImpl(DestinataireRepository destinataireRepository) {
        this.destinataireRepository = destinataireRepository;
    }

    /**
     * Save a destinataire.
     *
     * @param destinataire the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Destinataire save(Destinataire destinataire) {
        log.debug("Request to save Destinataire : {}", destinataire);
        return destinataireRepository.save(destinataire);
    }

    /**
     * Get all the destinataires.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Destinataire> findAll() {
        log.debug("Request to get all Destinataires");
        return destinataireRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the destinataires with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Destinataire> findAllWithEagerRelationships(Pageable pageable) {
        return destinataireRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one destinataire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Destinataire> findOne(Long id) {
        log.debug("Request to get Destinataire : {}", id);
        return destinataireRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the destinataire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Destinataire : {}", id);
        destinataireRepository.deleteById(id);
    }
}
