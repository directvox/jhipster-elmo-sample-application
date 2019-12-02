package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.Destinataire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Destinataire entity.
 */
@Repository
public interface DestinataireRepository extends JpaRepository<Destinataire, Long> {

    @Query(value = "select distinct destinataire from Destinataire destinataire left join fetch destinataire.specCliniques",
        countQuery = "select count(distinct destinataire) from Destinataire destinataire")
    Page<Destinataire> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct destinataire from Destinataire destinataire left join fetch destinataire.specCliniques")
    List<Destinataire> findAllWithEagerRelationships();

    @Query("select destinataire from Destinataire destinataire left join fetch destinataire.specCliniques where destinataire.id =:id")
    Optional<Destinataire> findOneWithEagerRelationships(@Param("id") Long id);

}
