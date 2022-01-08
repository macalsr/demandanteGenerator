package com.nexty.demandante.service;

import com.nexty.demandante.domain.Cidade;
import com.nexty.demandante.repository.CidadeRepository;
import com.nexty.demandante.service.dto.CidadeDto;
import com.nexty.demandante.service.mapper.CidadeMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private CidadeMapper cidadeMapper;

    public Cidade findById(Long id) {
        return this.cidadeRepository.findByDemandanteId(id).orElseThrow(
                () -> new UsernameNotFoundException("Address Not Found with -> demandanteId: " + id));
    }
    public CidadeDto create(CidadeDto cidadeDto){
        Cidade cidade = this.cidadeMapper.toEntity(cidadeDto);
        return cidadeMapper.toDto(cidadeRepository.save(cidade));
    }
}
