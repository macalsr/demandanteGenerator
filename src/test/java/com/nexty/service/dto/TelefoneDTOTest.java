package com.nexty.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexty.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TelefoneDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TelefoneDTO.class);
        TelefoneDTO telefoneDTO1 = new TelefoneDTO();
        telefoneDTO1.setId(1L);
        TelefoneDTO telefoneDTO2 = new TelefoneDTO();
        assertThat(telefoneDTO1).isNotEqualTo(telefoneDTO2);
        telefoneDTO2.setId(telefoneDTO1.getId());
        assertThat(telefoneDTO1).isEqualTo(telefoneDTO2);
        telefoneDTO2.setId(2L);
        assertThat(telefoneDTO1).isNotEqualTo(telefoneDTO2);
        telefoneDTO1.setId(null);
        assertThat(telefoneDTO1).isNotEqualTo(telefoneDTO2);
    }
}
