package com.goelmo.elmo.service.impl;

import com.goelmo.elmo.service.SpecCliniqueService;
import com.goelmo.elmo.domain.SpecClinique;
import com.goelmo.elmo.repository.SpecCliniqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SpecClinique}.
 */
@Service
@Transactional
public class SpecCliniqueServiceImpl implements SpecCliniqueService {

    private final Logger log = LoggerFactory.getLogger(SpecCliniqueServiceImpl.class);

    private final SpecCliniqueRepository specCliniqueRepository;

    public SpecCliniqueServiceImpl(SpecCliniqueRepository specCliniqueRepository) {
        this.specCliniqueRepository = specCliniqueRepository;
    }

    /**
     * Save a specClinique.
     *
     * @param specClinique the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SpecClinique save(SpecClinique specClinique) {
        log.debug("Request to save SpecClinique : {}", specClinique);
        return specCliniqueRepository.save(specClinique);
    }

    /**
     * Get all the specCliniques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpecClinique> findAll(Pageable pageable) {
        log.debug("Request to get all SpecCliniques");
        return specCliniqueRepository.findAll(pageable);
    }


    /**
     * Get one specClinique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SpecClinique> findOne(Long id) {
        log.debug("Request to get SpecClinique : {}", id);
        return specCliniqueRepository.findById(id);
    }

    /**
     * Delete the specClinique by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SpecClinique : {}", id);
        specCliniqueRepository.deleteById(id);
    }
}
