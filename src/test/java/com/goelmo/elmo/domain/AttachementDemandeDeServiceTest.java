package com.goelmo.elmo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.goelmo.elmo.web.rest.TestUtil;

public class AttachementDemandeDeServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttachementDemandeDeService.class);
        AttachementDemandeDeService attachementDemandeDeService1 = new AttachementDemandeDeService();
        attachementDemandeDeService1.setId(1L);
        AttachementDemandeDeService attachementDemandeDeService2 = new AttachementDemandeDeService();
        attachementDemandeDeService2.setId(attachementDemandeDeService1.getId());
        assertThat(attachementDemandeDeService1).isEqualTo(attachementDemandeDeService2);
        attachementDemandeDeService2.setId(2L);
        assertThat(attachementDemandeDeService1).isNotEqualTo(attachementDemandeDeService2);
        attachementDemandeDeService1.setId(null);
        assertThat(attachementDemandeDeService1).isNotEqualTo(attachementDemandeDeService2);
    }
}
