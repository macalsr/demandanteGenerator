package com.nexty.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnderecoMapperTest {

    private EnderecoMapper enderecoMapper;

    @BeforeEach
    public void setUp() {
        enderecoMapper = new EnderecoMapperImpl();
    }
}
