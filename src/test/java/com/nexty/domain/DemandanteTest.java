package com.nexty.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexty.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DemandanteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demandante.class);
        Demandante demandante1 = new Demandante();
        demandante1.setId(1L);
        Demandante demandante2 = new Demandante();
        demandante2.setId(demandante1.getId());
        assertThat(demandante1).isEqualTo(demandante2);
        demandante2.setId(2L);
        assertThat(demandante1).isNotEqualTo(demandante2);
        demandante1.setId(null);
        assertThat(demandante1).isNotEqualTo(demandante2);
    }
}
