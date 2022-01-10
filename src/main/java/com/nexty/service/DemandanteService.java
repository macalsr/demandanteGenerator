package com.nexty.service;

import com.nexty.domain.Demandante;
import com.nexty.repository.DemandanteRepository;
import com.nexty.service.dto.DemandanteDTO;
import com.nexty.service.mapper.DemandanteMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Demandante}.
 */
@Service
@Transactional
public class DemandanteService {

    private final Logger log = LoggerFactory.getLogger(DemandanteService.class);

    private final DemandanteRepository demandanteRepository;

    private final DemandanteMapper demandanteMapper;

    public DemandanteService(DemandanteRepository demandanteRepository, DemandanteMapper demandanteMapper) {
        this.demandanteRepository = demandanteRepository;
        this.demandanteMapper = demandanteMapper;
    }

    /**
     * Save a demandante.
     *
     * @param demandanteDTO the entity to save.
     * @return the persisted entity.
     */
    public DemandanteDTO save(DemandanteDTO demandanteDTO) {
        log.debug("Request to save Demandante : {}", demandanteDTO);
        Demandante demandante = demandanteMapper.toEntity(demandanteDTO);
        demandante = demandanteRepository.save(demandante);
        return demandanteMapper.toDto(demandante);
    }

    /**
     * Partially update a demandante.
     *
     * @param demandanteDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DemandanteDTO> partialUpdate(DemandanteDTO demandanteDTO) {
        log.debug("Request to partially update Demandante : {}", demandanteDTO);

        return demandanteRepository
            .findById(demandanteDTO.getId())
            .map(existingDemandante -> {
                demandanteMapper.partialUpdate(existingDemandante, demandanteDTO);

                return existingDemandante;
            })
            .map(demandanteRepository::save)
            .map(demandanteMapper::toDto);
    }

    /**
     * Get all the demandantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandanteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Demandantes");
        return demandanteRepository.findAll(pageable).map(demandanteMapper::toDto);
    }

    /**
     * Get one demandante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DemandanteDTO> findOne(Long id) {
        log.debug("Request to get Demandante : {}", id);
        return demandanteRepository.findById(id).map(demandanteMapper::toDto);
    }

    /**
     * Delete the demandante by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Demandante : {}", id);
        demandanteRepository.deleteById(id);
    }
}
