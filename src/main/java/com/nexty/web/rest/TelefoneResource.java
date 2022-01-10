package com.nexty.web.rest;

import com.nexty.repository.TelefoneRepository;
import com.nexty.service.TelefoneService;
import com.nexty.service.dto.TelefoneDTO;
import com.nexty.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.nexty.domain.Telefone}.
 */
@RestController
@RequestMapping("/api")
public class TelefoneResource {

    private final Logger log = LoggerFactory.getLogger(TelefoneResource.class);

    private static final String ENTITY_NAME = "telefone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TelefoneService telefoneService;

    private final TelefoneRepository telefoneRepository;

    public TelefoneResource(TelefoneService telefoneService, TelefoneRepository telefoneRepository) {
        this.telefoneService = telefoneService;
        this.telefoneRepository = telefoneRepository;
    }

    /**
     * {@code POST  /telefones} : Create a new telefone.
     *
     * @param telefoneDTO the telefoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new telefoneDTO, or with status {@code 400 (Bad Request)} if the telefone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/telefones")
    public ResponseEntity<TelefoneDTO> createTelefone(@RequestBody TelefoneDTO telefoneDTO) throws URISyntaxException {
        log.debug("REST request to save Telefone : {}", telefoneDTO);
        if (telefoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new telefone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TelefoneDTO result = telefoneService.save(telefoneDTO);
        return ResponseEntity
            .created(new URI("/api/telefones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /telefones/:id} : Updates an existing telefone.
     *
     * @param id the id of the telefoneDTO to save.
     * @param telefoneDTO the telefoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated telefoneDTO,
     * or with status {@code 400 (Bad Request)} if the telefoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the telefoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/telefones/{id}")
    public ResponseEntity<TelefoneDTO> updateTelefone(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TelefoneDTO telefoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Telefone : {}, {}", id, telefoneDTO);
        if (telefoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, telefoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!telefoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TelefoneDTO result = telefoneService.save(telefoneDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, telefoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /telefones/:id} : Partial updates given fields of an existing telefone, field will ignore if it is null
     *
     * @param id the id of the telefoneDTO to save.
     * @param telefoneDTO the telefoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated telefoneDTO,
     * or with status {@code 400 (Bad Request)} if the telefoneDTO is not valid,
     * or with status {@code 404 (Not Found)} if the telefoneDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the telefoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/telefones/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TelefoneDTO> partialUpdateTelefone(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TelefoneDTO telefoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Telefone partially : {}, {}", id, telefoneDTO);
        if (telefoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, telefoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!telefoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TelefoneDTO> result = telefoneService.partialUpdate(telefoneDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, telefoneDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /telefones} : get all the telefones.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of telefones in body.
     */
    @GetMapping("/telefones")
    public List<TelefoneDTO> getAllTelefones(@RequestParam(required = false) String filter) {
        if ("demandante-is-null".equals(filter)) {
            log.debug("REST request to get all Telefones where demandante is null");
            return telefoneService.findAllWhereDemandanteIsNull();
        }
        log.debug("REST request to get all Telefones");
        return telefoneService.findAll();
    }

    /**
     * {@code GET  /telefones/:id} : get the "id" telefone.
     *
     * @param id the id of the telefoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the telefoneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/telefones/{id}")
    public ResponseEntity<TelefoneDTO> getTelefone(@PathVariable Long id) {
        log.debug("REST request to get Telefone : {}", id);
        Optional<TelefoneDTO> telefoneDTO = telefoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(telefoneDTO);
    }

    /**
     * {@code DELETE  /telefones/:id} : delete the "id" telefone.
     *
     * @param id the id of the telefoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/telefones/{id}")
    public ResponseEntity<Void> deleteTelefone(@PathVariable Long id) {
        log.debug("REST request to delete Telefone : {}", id);
        telefoneService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
