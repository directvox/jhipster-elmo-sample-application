package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class DestinataireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Destinataire.class);
        Destinataire destinataire1 = new Destinataire();
        destinataire1.setId(1L);
        Destinataire destinataire2 = new Destinataire();
        destinataire2.setId(destinataire1.getId());
        assertThat(destinataire1).isEqualTo(destinataire2);
        destinataire2.setId(2L);
        assertThat(destinataire1).isNotEqualTo(destinataire2);
        destinataire1.setId(null);
        assertThat(destinataire1).isNotEqualTo(destinataire2);
    }
}
