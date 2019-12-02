package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.FormulaireEvaluation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormulaireEvaluation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulaireEvaluationRepository extends JpaRepository<FormulaireEvaluation, Long> {

}
