package com.goelmo.elmo.repository;
import com.goelmo.elmo.domain.Intervenant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Intervenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntervenantRepository extends JpaRepository<Intervenant, Long> {

}
