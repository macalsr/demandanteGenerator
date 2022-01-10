package com.nexty.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TelefoneMapperTest {

    private TelefoneMapper telefoneMapper;

    @BeforeEach
    public void setUp() {
        telefoneMapper = new TelefoneMapperImpl();
    }
}
