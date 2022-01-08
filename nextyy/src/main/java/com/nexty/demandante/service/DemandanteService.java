package com.nexty.demandante.service;

import com.nexty.demandante.domain.Demandante;
import com.nexty.demandante.repository.DemandanteRepository;
import com.nexty.demandante.service.dto.DemandanteDto;
import com.nexty.demandante.service.mapper.DemandanteMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DemandanteService {

    private DemandanteRepository demandanteRepository;

    private DemandanteMapper demandanteMapper;

    public Demandante findAll(Demandante demandante){
        return this.demandanteRepository.findAll(demandante);
    }

    public Demandante findById(Long id){
        return this.demandanteRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Demandante not found with -> id:" + id));
    }

    public Demandante findByCpf(String cpf){
        return this.demandanteRepository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("Demandante not found with -> cpf: " + cpf));
    }

    public DemandanteDto create(DemandanteDto demandanteDto){
        Demandante demandante = demandanteMapper.toEntity(demandanteDto);
        return demandanteMapper.toDto(persistir(demandante));
    }

    private Demandante persistir(Demandante demandante){
        return demandanteRepository.save(demandante);
    }

}
