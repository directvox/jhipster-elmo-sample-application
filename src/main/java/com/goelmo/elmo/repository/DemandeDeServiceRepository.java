package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.DemandeDeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DemandeDeService entity.
 */
@Repository
public interface DemandeDeServiceRepository extends JpaRepository<DemandeDeService, Long> {

    @Query(value = "select distinct demandeDeService from DemandeDeService demandeDeService left join fetch demandeDeService.formulaireEvaluations",
        countQuery = "select count(distinct demandeDeService) from DemandeDeService demandeDeService")
    Page<DemandeDeService> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct demandeDeService from DemandeDeService demandeDeService left join fetch demandeDeService.formulaireEvaluations")
    List<DemandeDeService> findAllWithEagerRelationships();

    @Query("select demandeDeService from DemandeDeService demandeDeService left join fetch demandeDeService.formulaireEvaluations where demandeDeService.id =:id")
    Optional<DemandeDeService> findOneWithEagerRelationships(@Param("id") Long id);

}
