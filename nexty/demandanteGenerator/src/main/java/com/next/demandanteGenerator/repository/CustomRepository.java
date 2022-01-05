package com.next.demandanteGenerator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CustomRepository<T> {
    Page<T> listSearch(Map<String, String> allParams, Pageable pageable, String entity, String addFilter);
}
