package com.nexty.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemandanteMapperTest {

    private DemandanteMapper demandanteMapper;

    @BeforeEach
    public void setUp() {
        demandanteMapper = new DemandanteMapperImpl();
    }
}
