package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class FormulaireEvaluationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulaireEvaluation.class);
        FormulaireEvaluation formulaireEvaluation1 = new FormulaireEvaluation();
        formulaireEvaluation1.setId(1L);
        FormulaireEvaluation formulaireEvaluation2 = new FormulaireEvaluation();
        formulaireEvaluation2.setId(formulaireEvaluation1.getId());
        assertThat(formulaireEvaluation1).isEqualTo(formulaireEvaluation2);
        formulaireEvaluation2.setId(2L);
        assertThat(formulaireEvaluation1).isNotEqualTo(formulaireEvaluation2);
        formulaireEvaluation1.setId(null);
        assertThat(formulaireEvaluation1).isNotEqualTo(formulaireEvaluation2);
    }
}
