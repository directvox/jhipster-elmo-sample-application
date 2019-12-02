package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.SpecClinique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SpecClinique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecCliniqueRepository extends JpaRepository<SpecClinique, Long> {

}
