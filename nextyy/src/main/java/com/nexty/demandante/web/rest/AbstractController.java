package com.nexty.demandante.web.rest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class AbstractController {

    protected URI buildUriForCreate(Long id, String path) {

        return UriComponentsBuilder.newInstance()
                .path(path)
                .buildAndExpand(id).toUri();

    }


}
