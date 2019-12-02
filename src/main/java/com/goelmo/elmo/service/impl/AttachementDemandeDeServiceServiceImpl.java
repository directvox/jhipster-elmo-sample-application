package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.AttachementDemandeDeServiceService;
import com.goelmo.elmo.domain.AttachementDemandeDeService;
import com.goelmo.elmo.repository.AttachementDemandeDeServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AttachementDemandeDeService}.
 */
@Service
@Transactional
public class AttachementDemandeDeServiceServiceImpl implements AttachementDemandeDeServiceService {

    private final Logger log = LoggerFactory.getLogger(AttachementDemandeDeServiceServiceImpl.class);

    private final AttachementDemandeDeServiceRepository attachementDemandeDeServiceRepository;

    public AttachementDemandeDeServiceServiceImpl(AttachementDemandeDeServiceRepository attachementDemandeDeServiceRepository) {
        this.attachementDemandeDeServiceRepository = attachementDemandeDeServiceRepository;
    }

    /**
     * Save a attachementDemandeDeService.
     *
     * @param attachementDemandeDeService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AttachementDemandeDeService save(AttachementDemandeDeService attachementDemandeDeService) {
        log.debug("Request to save AttachementDemandeDeService : {}", attachementDemandeDeService);
        return attachementDemandeDeServiceRepository.save(attachementDemandeDeService);
    }

    /**
     * Get all the attachementDemandeDeServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttachementDemandeDeService> findAll() {
        log.debug("Request to get all AttachementDemandeDeServices");
        return attachementDemandeDeServiceRepository.findAll();
    }


    /**
     * Get one attachementDemandeDeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttachementDemandeDeService> findOne(Long id) {
        log.debug("Request to get AttachementDemandeDeService : {}", id);
        return attachementDemandeDeServiceRepository.findById(id);
    }

    /**
     * Delete the attachementDemandeDeService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttachementDemandeDeService : {}", id);
        attachementDemandeDeServiceRepository.deleteById(id);
    }
}
