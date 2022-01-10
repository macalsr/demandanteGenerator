package com.nexty.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexty.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TelefoneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Telefone.class);
        Telefone telefone1 = new Telefone();
        telefone1.setId(1L);
        Telefone telefone2 = new Telefone();
        telefone2.setId(telefone1.getId());
        assertThat(telefone1).isEqualTo(telefone2);
        telefone2.setId(2L);
        assertThat(telefone1).isNotEqualTo(telefone2);
        telefone1.setId(null);
        assertThat(telefone1).isNotEqualTo(telefone2);
    }
}
