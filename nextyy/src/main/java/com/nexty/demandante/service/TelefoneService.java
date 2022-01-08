package com.nexty.demandante.service;

import com.nexty.demandante.domain.Demandante;
import com.nexty.demandante.domain.Telefone;
import com.nexty.demandante.repository.DemandanteRepository;
import com.nexty.demandante.repository.TelefoneRepository;
import com.nexty.demandante.service.dto.TelefoneDto;
import com.nexty.demandante.service.mapper.TelefoneMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TelefoneService {


    private DemandanteRepository demandanteRepository;

    private TelefoneRepository telefoneRepository;

    private TelefoneMapper telefoneMapper;

    public Telefone findById(Long id){
        return this.telefoneRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Telefone não encontrado -> id:" + id));
    }

    public TelefoneDto create(Long id, TelefoneDto telefoneDto){
        Demandante demandante = this.demandanteRepository.getById(id);
        Telefone telefone = this.telefoneMapper.toEntity(telefoneDto);
        return telefoneMapper.toDto(telefoneRepository.save(telefone));
    }




}
