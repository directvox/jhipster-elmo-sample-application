package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.ReponseDemandeDeService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReponseDemandeDeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReponseDemandeDeServiceRepository extends JpaRepository<ReponseDemandeDeService, Long> {

}
