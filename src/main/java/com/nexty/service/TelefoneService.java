package com.nexty.service;

import com.nexty.domain.Telefone;
import com.nexty.repository.TelefoneRepository;
import com.nexty.service.dto.TelefoneDTO;
import com.nexty.service.mapper.TelefoneMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Telefone}.
 */
@Service
@Transactional
public class TelefoneService {

    private final Logger log = LoggerFactory.getLogger(TelefoneService.class);

    private final TelefoneRepository telefoneRepository;

    private final TelefoneMapper telefoneMapper;

    public TelefoneService(TelefoneRepository telefoneRepository, TelefoneMapper telefoneMapper) {
        this.telefoneRepository = telefoneRepository;
        this.telefoneMapper = telefoneMapper;
    }

    /**
     * Save a telefone.
     *
     * @param telefoneDTO the entity to save.
     * @return the persisted entity.
     */
    public TelefoneDTO save(TelefoneDTO telefoneDTO) {
        log.debug("Request to save Telefone : {}", telefoneDTO);
        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
        telefone = telefoneRepository.save(telefone);
        return telefoneMapper.toDto(telefone);
    }

    /**
     * Partially update a telefone.
     *
     * @param telefoneDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TelefoneDTO> partialUpdate(TelefoneDTO telefoneDTO) {
        log.debug("Request to partially update Telefone : {}", telefoneDTO);

        return telefoneRepository
            .findById(telefoneDTO.getId())
            .map(existingTelefone -> {
                telefoneMapper.partialUpdate(existingTelefone, telefoneDTO);

                return existingTelefone;
            })
            .map(telefoneRepository::save)
            .map(telefoneMapper::toDto);
    }

    /**
     * Get all the telefones.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TelefoneDTO> findAll() {
        log.debug("Request to get all Telefones");
        return telefoneRepository.findAll().stream().map(telefoneMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the telefones where Demandante is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TelefoneDTO> findAllWhereDemandanteIsNull() {
        log.debug("Request to get all telefones where Demandante is null");
        return StreamSupport
            .stream(telefoneRepository.findAll().spliterator(), false)
            .filter(telefone -> telefone.getDemandante() == null)
            .map(telefoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one telefone by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TelefoneDTO> findOne(Long id) {
        log.debug("Request to get Telefone : {}", id);
        return telefoneRepository.findById(id).map(telefoneMapper::toDto);
    }

    /**
     * Delete the telefone by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Telefone : {}", id);
        telefoneRepository.deleteById(id);
    }
}
