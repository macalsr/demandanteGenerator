package com.next.demandanteGenerator.service;

import com.next.demandanteGenerator.model.Demandante;
import com.next.demandanteGenerator.model.Sexo;
import com.next.demandanteGenerator.repository.DemandanteRepository;
import com.next.demandanteGenerator.service.dto.DemandanteDto;
import com.next.demandanteGenerator.service.mapper.DemandanteMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DemandanteService {

    private final DemandanteRepository demandanteRepository;
    private final DemandanteMapper demandanteMapper;

    static Enum<Sexo> sexoEnum;

    public Demandante findById(Long id){
        return this.demandanteRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Demandante not found with -> id:" + id));
    }

    public Demandante findByCpf(String cpf){
        return this.demandanteRepository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("Demandante not found with -> cpf: " + cpf));
    }

    public DemandanteDto create( DemandanteDto demandanteDto){
        Demandante demandante = demandanteMapper.toEntity(demandanteDto);
        if(demandanteRepository.existsByCpf(demandante.getCpf())){
            throw new RuntimeException("Cpf já existe");
        }
        return demandanteMapper.toDto(persistir(demandante));
    }

    private Demandante persistir(Demandante demandante){
        return demandanteRepository.save(demandante);
    }

}
