package com.nexty.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexty.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DemandanteDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandanteDTO.class);
        DemandanteDTO demandanteDTO1 = new DemandanteDTO();
        demandanteDTO1.setId(1L);
        DemandanteDTO demandanteDTO2 = new DemandanteDTO();
        assertThat(demandanteDTO1).isNotEqualTo(demandanteDTO2);
        demandanteDTO2.setId(demandanteDTO1.getId());
        assertThat(demandanteDTO1).isEqualTo(demandanteDTO2);
        demandanteDTO2.setId(2L);
        assertThat(demandanteDTO1).isNotEqualTo(demandanteDTO2);
        demandanteDTO1.setId(null);
        assertThat(demandanteDTO1).isNotEqualTo(demandanteDTO2);
    }
}
