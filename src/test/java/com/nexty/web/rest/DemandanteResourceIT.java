package com.nexty.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nexty.IntegrationTest;
import com.nexty.domain.Demandante;
import com.nexty.domain.enumeration.Sexo;
import com.nexty.repository.DemandanteRepository;
import com.nexty.service.dto.DemandanteDTO;
import com.nexty.service.mapper.DemandanteMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DemandanteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DemandanteResourceIT {

    private static final String DEFAULT_NM_DEMANDANTE = "AAAAAAAAAA";
    private static final String UPDATED_NM_DEMANDANTE = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final Integer DEFAULT_CD_TIPO_DEMANDANTE = 1;
    private static final Integer UPDATED_CD_TIPO_DEMANDANTE = 2;

    private static final Integer DEFAULT_CD_ATENDIMENTO = 1;
    private static final Integer UPDATED_CD_ATENDIMENTO = 2;

    private static final Sexo DEFAULT_SEXO = Sexo.Feminino;
    private static final Sexo UPDATED_SEXO = Sexo.Masculino;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String ENTITY_API_URL = "/api/demandantes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DemandanteRepository demandanteRepository;

    @Autowired
    private DemandanteMapper demandanteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandanteMockMvc;

    private Demandante demandante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demandante createEntity(EntityManager em) {
        Demandante demandante = new Demandante()
            .nmDemandante(DEFAULT_NM_DEMANDANTE)
            .cpf(DEFAULT_CPF)
            .cdTipoDemandante(DEFAULT_CD_TIPO_DEMANDANTE)
            .cdAtendimento(DEFAULT_CD_ATENDIMENTO)
            .sexo(DEFAULT_SEXO)
            .active(DEFAULT_ACTIVE);
        return demandante;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demandante createUpdatedEntity(EntityManager em) {
        Demandante demandante = new Demandante()
            .nmDemandante(UPDATED_NM_DEMANDANTE)
            .cpf(UPDATED_CPF)
            .cdTipoDemandante(UPDATED_CD_TIPO_DEMANDANTE)
            .cdAtendimento(UPDATED_CD_ATENDIMENTO)
            .sexo(UPDATED_SEXO)
            .active(UPDATED_ACTIVE);
        return demandante;
    }

    @BeforeEach
    public void initTest() {
        demandante = createEntity(em);
    }

    @Test
    @Transactional
    void createDemandante() throws Exception {
        int databaseSizeBeforeCreate = demandanteRepository.findAll().size();
        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);
        restDemandanteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandanteDTO)))
            .andExpect(status().isCreated());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeCreate + 1);
        Demandante testDemandante = demandanteList.get(demandanteList.size() - 1);
        assertThat(testDemandante.getNmDemandante()).isEqualTo(DEFAULT_NM_DEMANDANTE);
        assertThat(testDemandante.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testDemandante.getCdTipoDemandante()).isEqualTo(DEFAULT_CD_TIPO_DEMANDANTE);
        assertThat(testDemandante.getCdAtendimento()).isEqualTo(DEFAULT_CD_ATENDIMENTO);
        assertThat(testDemandante.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testDemandante.getActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    void createDemandanteWithExistingId() throws Exception {
        // Create the Demandante with an existing ID
        demandante.setId(1L);
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        int databaseSizeBeforeCreate = demandanteRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandanteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandanteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNmDemandanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandanteRepository.findAll().size();
        // set the field null
        demandante.setNmDemandante(null);

        // Create the Demandante, which fails.
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        restDemandanteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandanteDTO)))
            .andExpect(status().isBadRequest());

        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCpfIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandanteRepository.findAll().size();
        // set the field null
        demandante.setCpf(null);

        // Create the Demandante, which fails.
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        restDemandanteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandanteDTO)))
            .andExpect(status().isBadRequest());

        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDemandantes() throws Exception {
        // Initialize the database
        demandanteRepository.saveAndFlush(demandante);

        // Get all the demandanteList
        restDemandanteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nmDemandante").value(hasItem(DEFAULT_NM_DEMANDANTE)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].cdTipoDemandante").value(hasItem(DEFAULT_CD_TIPO_DEMANDANTE)))
            .andExpect(jsonPath("$.[*].cdAtendimento").value(hasItem(DEFAULT_CD_ATENDIMENTO)))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())));
    }

    @Test
    @Transactional
    void getDemandante() throws Exception {
        // Initialize the database
        demandanteRepository.saveAndFlush(demandante);

        // Get the demandante
        restDemandanteMockMvc
            .perform(get(ENTITY_API_URL_ID, demandante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandante.getId().intValue()))
            .andExpect(jsonPath("$.nmDemandante").value(DEFAULT_NM_DEMANDANTE))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.cdTipoDemandante").value(DEFAULT_CD_TIPO_DEMANDANTE))
            .andExpect(jsonPath("$.cdAtendimento").value(DEFAULT_CD_ATENDIMENTO))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingDemandante() throws Exception {
        // Get the demandante
        restDemandanteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDemandante() throws Exception {
        // Initialize the database
        demandanteRepository.saveAndFlush(demandante);

        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();

        // Update the demandante
        Demandante updatedDemandante = demandanteRepository.findById(demandante.getId()).get();
        // Disconnect from session so that the updates on updatedDemandante are not directly saved in db
        em.detach(updatedDemandante);
        updatedDemandante
            .nmDemandante(UPDATED_NM_DEMANDANTE)
            .cpf(UPDATED_CPF)
            .cdTipoDemandante(UPDATED_CD_TIPO_DEMANDANTE)
            .cdAtendimento(UPDATED_CD_ATENDIMENTO)
            .sexo(UPDATED_SEXO)
            .active(UPDATED_ACTIVE);
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(updatedDemandante);

        restDemandanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandanteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandanteDTO))
            )
            .andExpect(status().isOk());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
        Demandante testDemandante = demandanteList.get(demandanteList.size() - 1);
        assertThat(testDemandante.getNmDemandante()).isEqualTo(UPDATED_NM_DEMANDANTE);
        assertThat(testDemandante.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testDemandante.getCdTipoDemandante()).isEqualTo(UPDATED_CD_TIPO_DEMANDANTE);
        assertThat(testDemandante.getCdAtendimento()).isEqualTo(UPDATED_CD_ATENDIMENTO);
        assertThat(testDemandante.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testDemandante.getActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    void putNonExistingDemandante() throws Exception {
        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();
        demandante.setId(count.incrementAndGet());

        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandanteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemandante() throws Exception {
        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();
        demandante.setId(count.incrementAndGet());

        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemandante() throws Exception {
        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();
        demandante.setId(count.incrementAndGet());

        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandanteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandanteDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDemandanteWithPatch() throws Exception {
        // Initialize the database
        demandanteRepository.saveAndFlush(demandante);

        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();

        // Update the demandante using partial update
        Demandante partialUpdatedDemandante = new Demandante();
        partialUpdatedDemandante.setId(demandante.getId());

        partialUpdatedDemandante.cpf(UPDATED_CPF);

        restDemandanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandante.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemandante))
            )
            .andExpect(status().isOk());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
        Demandante testDemandante = demandanteList.get(demandanteList.size() - 1);
        assertThat(testDemandante.getNmDemandante()).isEqualTo(DEFAULT_NM_DEMANDANTE);
        assertThat(testDemandante.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testDemandante.getCdTipoDemandante()).isEqualTo(DEFAULT_CD_TIPO_DEMANDANTE);
        assertThat(testDemandante.getCdAtendimento()).isEqualTo(DEFAULT_CD_ATENDIMENTO);
        assertThat(testDemandante.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testDemandante.getActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    void fullUpdateDemandanteWithPatch() throws Exception {
        // Initialize the database
        demandanteRepository.saveAndFlush(demandante);

        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();

        // Update the demandante using partial update
        Demandante partialUpdatedDemandante = new Demandante();
        partialUpdatedDemandante.setId(demandante.getId());

        partialUpdatedDemandante
            .nmDemandante(UPDATED_NM_DEMANDANTE)
            .cpf(UPDATED_CPF)
            .cdTipoDemandante(UPDATED_CD_TIPO_DEMANDANTE)
            .cdAtendimento(UPDATED_CD_ATENDIMENTO)
            .sexo(UPDATED_SEXO)
            .active(UPDATED_ACTIVE);

        restDemandanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandante.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemandante))
            )
            .andExpect(status().isOk());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
        Demandante testDemandante = demandanteList.get(demandanteList.size() - 1);
        assertThat(testDemandante.getNmDemandante()).isEqualTo(UPDATED_NM_DEMANDANTE);
        assertThat(testDemandante.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testDemandante.getCdTipoDemandante()).isEqualTo(UPDATED_CD_TIPO_DEMANDANTE);
        assertThat(testDemandante.getCdAtendimento()).isEqualTo(UPDATED_CD_ATENDIMENTO);
        assertThat(testDemandante.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testDemandante.getActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    void patchNonExistingDemandante() throws Exception {
        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();
        demandante.setId(count.incrementAndGet());

        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demandanteDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demandanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemandante() throws Exception {
        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();
        demandante.setId(count.incrementAndGet());

        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demandanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemandante() throws Exception {
        int databaseSizeBeforeUpdate = demandanteRepository.findAll().size();
        demandante.setId(count.incrementAndGet());

        // Create the Demandante
        DemandanteDTO demandanteDTO = demandanteMapper.toDto(demandante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandanteMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(demandanteDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demandante in the database
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDemandante() throws Exception {
        // Initialize the database
        demandanteRepository.saveAndFlush(demandante);

        int databaseSizeBeforeDelete = demandanteRepository.findAll().size();

        // Delete the demandante
        restDemandanteMockMvc
            .perform(delete(ENTITY_API_URL_ID, demandante.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Demandante> demandanteList = demandanteRepository.findAll();
        assertThat(demandanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
