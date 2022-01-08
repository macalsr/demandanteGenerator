package com.nexty.demandante.web.rest;

import com.nexty.demandante.service.TelefoneService;
import com.nexty.demandante.service.dto.TelefoneDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping("/v1/telefone/")
@AllArgsConstructor
public class TelefoneController extends AbstractController {

    private TelefoneService telefoneService;

    @GetMapping("/")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        this.telefoneService.findById(id);
        return ResponseEntity
            .ok(new TelefoneDto());
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@PathVariable Long id, @Validated @RequestBody TelefoneDto telefoneDto) {
        TelefoneDto telefoneDto1 = telefoneService.create(id, telefoneDto);
        return ResponseEntity.created(URI.create("/v1/telefone/filtro?id=")).body(telefoneDto1);
    }
}

