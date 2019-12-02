package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.ReponseDemandeDeServiceService;
import com.goelmo.elmo.domain.ReponseDemandeDeService;
import com.goelmo.elmo.repository.ReponseDemandeDeServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ReponseDemandeDeService}.
 */
@Service
@Transactional
public class ReponseDemandeDeServiceServiceImpl implements ReponseDemandeDeServiceService {

    private final Logger log = LoggerFactory.getLogger(ReponseDemandeDeServiceServiceImpl.class);

    private final ReponseDemandeDeServiceRepository reponseDemandeDeServiceRepository;

    public ReponseDemandeDeServiceServiceImpl(ReponseDemandeDeServiceRepository reponseDemandeDeServiceRepository) {
        this.reponseDemandeDeServiceRepository = reponseDemandeDeServiceRepository;
    }

    /**
     * Save a reponseDemandeDeService.
     *
     * @param reponseDemandeDeService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReponseDemandeDeService save(ReponseDemandeDeService reponseDemandeDeService) {
        log.debug("Request to save ReponseDemandeDeService : {}", reponseDemandeDeService);
        return reponseDemandeDeServiceRepository.save(reponseDemandeDeService);
    }

    /**
     * Get all the reponseDemandeDeServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReponseDemandeDeService> findAll() {
        log.debug("Request to get all ReponseDemandeDeServices");
        return reponseDemandeDeServiceRepository.findAll();
    }


    /**
     * Get one reponseDemandeDeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReponseDemandeDeService> findOne(Long id) {
        log.debug("Request to get ReponseDemandeDeService : {}", id);
        return reponseDemandeDeServiceRepository.findById(id);
    }

    /**
     * Delete the reponseDemandeDeService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReponseDemandeDeService : {}", id);
        reponseDemandeDeServiceRepository.deleteById(id);
    }
}
