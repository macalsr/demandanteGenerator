package com.nexty.demandante.web.rest;


import com.nexty.demandante.domain.Cidade;
import com.nexty.demandante.domain.Demandante;
import com.nexty.demandante.service.CidadeService;
import com.nexty.demandante.service.dto.CidadeDto;
import com.nexty.demandante.service.dto.DemandanteDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RequestMapping("/v1/cidade/")
@RestController
@AllArgsConstructor
public class CidadeController extends AbstractController {

    private CidadeService cidadeService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Cidade cidade = this.cidadeService.findById(id);

        return ResponseEntity
                .ok(new CidadeDto());

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Validated @RequestBody CidadeDto cidadeDto){
        CidadeDto save = cidadeService.create(cidadeDto);
        return ResponseEntity.created(URI.create("/v1/cidade/filtro?id=")).body(save);
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
