package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.AttachementDemandeDeService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AttachementDemandeDeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttachementDemandeDeServiceRepository extends JpaRepository<AttachementDemandeDeService, Long> {

}
