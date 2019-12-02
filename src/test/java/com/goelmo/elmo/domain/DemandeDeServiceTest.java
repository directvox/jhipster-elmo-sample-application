package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class DemandeDeServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeDeService.class);
        DemandeDeService demandeDeService1 = new DemandeDeService();
        demandeDeService1.setId(1L);
        DemandeDeService demandeDeService2 = new DemandeDeService();
        demandeDeService2.setId(demandeDeService1.getId());
        assertThat(demandeDeService1).isEqualTo(demandeDeService2);
        demandeDeService2.setId(2L);
        assertThat(demandeDeService1).isNotEqualTo(demandeDeService2);
        demandeDeService1.setId(null);
        assertThat(demandeDeService1).isNotEqualTo(demandeDeService2);
    }
}
