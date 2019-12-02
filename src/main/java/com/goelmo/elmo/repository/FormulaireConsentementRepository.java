package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.FormulaireConsentement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormulaireConsentement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulaireConsentementRepository extends JpaRepository<FormulaireConsentement, Long> {

}
