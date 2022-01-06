package com.next.demandanteGenerator.controller;


import com.next.demandanteGenerator.service.DemandanteService;
import com.next.demandanteGenerator.service.dto.DemandanteDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/v1/demandante/")
@RestController
@AllArgsConstructor
public class DemandanteController extends AbstractController {

    private final DemandanteService demandanteService;

    @GetMapping("/")
    public ResponseEntity<?> findById(@PathVariable Long id){
        this.demandanteService.findById(id);
        return ResponseEntity
                .ok(new DemandanteDto());
    }
    @GetMapping("/cpf/")
    public ResponseEntity<?> findByCpf(@PathVariable String cpf){
        this.demandanteService.findByCpf(cpf);
        return ResponseEntity
                .ok(new DemandanteDto());
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Validated @RequestBody DemandanteDto demandanteDto){
        DemandanteDto save = demandanteService.create(demandanteDto);
        return ResponseEntity.created(URI.create("/v1/demandante/filtro?id=" + save.getId())).body(save);
    }

}
