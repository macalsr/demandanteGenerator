package com.next.demandanteGenerator.service;

import com.google.gson.Gson;
import com.next.demandanteGenerator.model.Cidade;
import com.next.demandanteGenerator.model.Demandante;
import com.next.demandanteGenerator.repository.CidadeRepository;
import com.next.demandanteGenerator.repository.DemandanteRepository;
import com.next.demandanteGenerator.service.dto.CidadeDto;
import com.next.demandanteGenerator.service.mapper.CidadeMapper;
import com.next.demandanteGenerator.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CidadeService {

    @Autowired
    private DemandanteRepository demandanteRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeMapper cidadeMapper;

    public Cidade findById(Long demandanteId) {
        return this.cidadeRepository.findByDemandanteId(demandanteId).orElseThrow(
                () -> new UsernameNotFoundException("Address Not Found with -> demandanteId: " + demandanteId));
    }
    public CidadeDto create( CidadeDto cidadeDto){
        Cidade cidade = this.cidadeMapper.toEntity(cidadeDto);
        return cidadeMapper.toDto(cidadeRepository.save(cidade));
    }
}
