package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class ReponseDemandeDeServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReponseDemandeDeService.class);
        ReponseDemandeDeService reponseDemandeDeService1 = new ReponseDemandeDeService();
        reponseDemandeDeService1.setId(1L);
        ReponseDemandeDeService reponseDemandeDeService2 = new ReponseDemandeDeService();
        reponseDemandeDeService2.setId(reponseDemandeDeService1.getId());
        assertThat(reponseDemandeDeService1).isEqualTo(reponseDemandeDeService2);
        reponseDemandeDeService2.setId(2L);
        assertThat(reponseDemandeDeService1).isNotEqualTo(reponseDemandeDeService2);
        reponseDemandeDeService1.setId(null);
        assertThat(reponseDemandeDeService1).isNotEqualTo(reponseDemandeDeService2);
    }
}
