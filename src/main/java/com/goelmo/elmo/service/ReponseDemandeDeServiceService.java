package com.goelmo.elmo.service;

import com.goelmo.elmo.domain.ReponseDemandeDeService;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ReponseDemandeDeService}.
 */
public interface ReponseDemandeDeServiceService {

    /**
     * Save a reponseDemandeDeService.
     *
     * @param reponseDemandeDeService the entity to save.
     * @return the persisted entity.
     */
    ReponseDemandeDeService save(ReponseDemandeDeService reponseDemandeDeService);

    /**
     * Get all the reponseDemandeDeServices.
     *
     * @return the list of entities.
     */
    List<ReponseDemandeDeService> findAll();


    /**
     * Get the "id" reponseDemandeDeService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReponseDemandeDeService> findOne(Long id);

    /**
     * Delete the "id" reponseDemandeDeService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
