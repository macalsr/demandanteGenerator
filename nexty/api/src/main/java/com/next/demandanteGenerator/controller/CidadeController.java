package com.next.demandanteGenerator.controller;


import com.next.demandanteGenerator.model.Cidade;
import com.next.demandanteGenerator.model.Demandante;
import com.next.demandanteGenerator.service.CidadeService;
import com.next.demandanteGenerator.service.dto.CidadeDto;
import com.next.demandanteGenerator.service.dto.DemandanteDto;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/v1/cidade/")
@RestController
@AllArgsConstructor
public class CidadeController extends AbstractController {

    private final CidadeService cidadeService;


    @GetMapping("/")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Cidade cidade = this.cidadeService.findById(id);

        return ResponseEntity
                .ok(new CidadeDto());

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Validated @RequestBody CidadeDto cidadeDto){
        CidadeDto save = cidadeService.create(cidadeDto);
        return ResponseEntity.created(URI.create("/v1/cidade/filtro?id=" + save.getId())).body(save);
    }
}
