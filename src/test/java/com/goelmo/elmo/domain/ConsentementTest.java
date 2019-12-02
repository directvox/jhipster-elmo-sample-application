package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class ConsentementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Consentement.class);
        Consentement consentement1 = new Consentement();
        consentement1.setId(1L);
        Consentement consentement2 = new Consentement();
        consentement2.setId(consentement1.getId());
        assertThat(consentement1).isEqualTo(consentement2);
        consentement2.setId(2L);
        assertThat(consentement1).isNotEqualTo(consentement2);
        consentement1.setId(null);
        assertThat(consentement1).isNotEqualTo(consentement2);
    }
}
