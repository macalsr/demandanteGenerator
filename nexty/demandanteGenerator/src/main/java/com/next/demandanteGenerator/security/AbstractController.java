package com.next.demandanteGenerator.security;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public abstract class AbstractController {

    protected URI buildUriForCreate(Long id, String path) {

        return UriComponentsBuilder.newInstance()
                .path(path)
                .buildAndExpand(id).toUri();

    }


    protected abstract ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception;
}