package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class FormulaireConsentementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulaireConsentement.class);
        FormulaireConsentement formulaireConsentement1 = new FormulaireConsentement();
        formulaireConsentement1.setId(1L);
        FormulaireConsentement formulaireConsentement2 = new FormulaireConsentement();
        formulaireConsentement2.setId(formulaireConsentement1.getId());
        assertThat(formulaireConsentement1).isEqualTo(formulaireConsentement2);
        formulaireConsentement2.setId(2L);
        assertThat(formulaireConsentement1).isNotEqualTo(formulaireConsentement2);
        formulaireConsentement1.setId(null);
        assertThat(formulaireConsentement1).isNotEqualTo(formulaireConsentement2);
    }
}
