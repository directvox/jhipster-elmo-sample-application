package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class IntervenantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Intervenant.class);
        Intervenant intervenant1 = new Intervenant();
        intervenant1.setId(1L);
        Intervenant intervenant2 = new Intervenant();
        intervenant2.setId(intervenant1.getId());
        assertThat(intervenant1).isEqualTo(intervenant2);
        intervenant2.setId(2L);
        assertThat(intervenant1).isNotEqualTo(intervenant2);
        intervenant1.setId(null);
        assertThat(intervenant1).isNotEqualTo(intervenant2);
    }
}
