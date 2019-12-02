package com.goelmo.elmo.service;

import com.goelmo.elmo.domain.AttachementDemandeDeService;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link AttachementDemandeDeService}.
 */
public interface AttachementDemandeDeServiceService {

    /**
     * Save a attachementDemandeDeService.
     *
     * @param attachementDemandeDeService the entity to save.
     * @return the persisted entity.
     */
    AttachementDemandeDeService save(AttachementDemandeDeService attachementDemandeDeService);

    /**
     * Get all the attachementDemandeDeServices.
     *
     * @return the list of entities.
     */
    List<AttachementDemandeDeService> findAll();


    /**
     * Get the "id" attachementDemandeDeService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AttachementDemandeDeService> findOne(Long id);

    /**
     * Delete the "id" attachementDemandeDeService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
