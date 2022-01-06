package com.next.demandanteGenerator.service;

import com.next.demandanteGenerator.model.Demandante;
import com.next.demandanteGenerator.model.Telefone;
import com.next.demandanteGenerator.repository.DemandanteRepository;
import com.next.demandanteGenerator.repository.TelefoneRepository;
import com.next.demandanteGenerator.service.dto.TelefoneDto;
import com.next.demandanteGenerator.service.mapper.TelefoneMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TelefoneService {

    @Autowired
    private DemandanteRepository demandanteRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    private final TelefoneMapper telefoneMapper;

    public Telefone findById(Long id){
        return this.telefoneRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Telefone não encontrado -> id:" + id));
    }

    public TelefoneDto create(Long demandanteId, TelefoneDto telefoneDto){
        Demandante demandante = this.demandanteRepository.getById(demandanteId);
        Telefone telefone = this.telefoneMapper.toEntity(telefoneDto);
        return telefoneMapper.toDto(telefoneRepository.save(telefone));
    }




}
