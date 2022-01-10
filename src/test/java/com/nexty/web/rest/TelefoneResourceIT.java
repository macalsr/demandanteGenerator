package com.nexty.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nexty.IntegrationTest;
import com.nexty.domain.Telefone;
import com.nexty.repository.TelefoneRepository;
import com.nexty.service.dto.TelefoneDTO;
import com.nexty.service.mapper.TelefoneMapper;
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
 * Integration tests for the {@link TelefoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TelefoneResourceIT {

    private static final String DEFAULT_DDD = "AAAAAAAAAA";
    private static final String UPDATED_DDD = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TIPO_TELEFONE = 1;
    private static final Integer UPDATED_TIPO_TELEFONE = 2;

    private static final String ENTITY_API_URL = "/api/telefones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private TelefoneMapper telefoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTelefoneMockMvc;

    private Telefone telefone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Telefone createEntity(EntityManager em) {
        Telefone telefone = new Telefone().ddd(DEFAULT_DDD).telefone(DEFAULT_TELEFONE).tipoTelefone(DEFAULT_TIPO_TELEFONE);
        return telefone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Telefone createUpdatedEntity(EntityManager em) {
        Telefone telefone = new Telefone().ddd(UPDATED_DDD).telefone(UPDATED_TELEFONE).tipoTelefone(UPDATED_TIPO_TELEFONE);
        return telefone;
    }

    @BeforeEach
    public void initTest() {
        telefone = createEntity(em);
    }

    @Test
    @Transactional
    void createTelefone() throws Exception {
        int databaseSizeBeforeCreate = telefoneRepository.findAll().size();
        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);
        restTelefoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telefoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeCreate + 1);
        Telefone testTelefone = telefoneList.get(telefoneList.size() - 1);
        assertThat(testTelefone.getDdd()).isEqualTo(DEFAULT_DDD);
        assertThat(testTelefone.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testTelefone.getTipoTelefone()).isEqualTo(DEFAULT_TIPO_TELEFONE);
    }

    @Test
    @Transactional
    void createTelefoneWithExistingId() throws Exception {
        // Create the Telefone with an existing ID
        telefone.setId(1L);
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        int databaseSizeBeforeCreate = telefoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTelefoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telefoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTelefones() throws Exception {
        // Initialize the database
        telefoneRepository.saveAndFlush(telefone);

        // Get all the telefoneList
        restTelefoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(telefone.getId().intValue())))
            .andExpect(jsonPath("$.[*].ddd").value(hasItem(DEFAULT_DDD)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].tipoTelefone").value(hasItem(DEFAULT_TIPO_TELEFONE)));
    }

    @Test
    @Transactional
    void getTelefone() throws Exception {
        // Initialize the database
        telefoneRepository.saveAndFlush(telefone);

        // Get the telefone
        restTelefoneMockMvc
            .perform(get(ENTITY_API_URL_ID, telefone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(telefone.getId().intValue()))
            .andExpect(jsonPath("$.ddd").value(DEFAULT_DDD))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.tipoTelefone").value(DEFAULT_TIPO_TELEFONE));
    }

    @Test
    @Transactional
    void getNonExistingTelefone() throws Exception {
        // Get the telefone
        restTelefoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTelefone() throws Exception {
        // Initialize the database
        telefoneRepository.saveAndFlush(telefone);

        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();

        // Update the telefone
        Telefone updatedTelefone = telefoneRepository.findById(telefone.getId()).get();
        // Disconnect from session so that the updates on updatedTelefone are not directly saved in db
        em.detach(updatedTelefone);
        updatedTelefone.ddd(UPDATED_DDD).telefone(UPDATED_TELEFONE).tipoTelefone(UPDATED_TIPO_TELEFONE);
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(updatedTelefone);

        restTelefoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, telefoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(telefoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
        Telefone testTelefone = telefoneList.get(telefoneList.size() - 1);
        assertThat(testTelefone.getDdd()).isEqualTo(UPDATED_DDD);
        assertThat(testTelefone.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testTelefone.getTipoTelefone()).isEqualTo(UPDATED_TIPO_TELEFONE);
    }

    @Test
    @Transactional
    void putNonExistingTelefone() throws Exception {
        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();
        telefone.setId(count.incrementAndGet());

        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTelefoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, telefoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(telefoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTelefone() throws Exception {
        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();
        telefone.setId(count.incrementAndGet());

        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelefoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(telefoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTelefone() throws Exception {
        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();
        telefone.setId(count.incrementAndGet());

        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelefoneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(telefoneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTelefoneWithPatch() throws Exception {
        // Initialize the database
        telefoneRepository.saveAndFlush(telefone);

        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();

        // Update the telefone using partial update
        Telefone partialUpdatedTelefone = new Telefone();
        partialUpdatedTelefone.setId(telefone.getId());

        partialUpdatedTelefone.ddd(UPDATED_DDD).telefone(UPDATED_TELEFONE);

        restTelefoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTelefone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTelefone))
            )
            .andExpect(status().isOk());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
        Telefone testTelefone = telefoneList.get(telefoneList.size() - 1);
        assertThat(testTelefone.getDdd()).isEqualTo(UPDATED_DDD);
        assertThat(testTelefone.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testTelefone.getTipoTelefone()).isEqualTo(DEFAULT_TIPO_TELEFONE);
    }

    @Test
    @Transactional
    void fullUpdateTelefoneWithPatch() throws Exception {
        // Initialize the database
        telefoneRepository.saveAndFlush(telefone);

        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();

        // Update the telefone using partial update
        Telefone partialUpdatedTelefone = new Telefone();
        partialUpdatedTelefone.setId(telefone.getId());

        partialUpdatedTelefone.ddd(UPDATED_DDD).telefone(UPDATED_TELEFONE).tipoTelefone(UPDATED_TIPO_TELEFONE);

        restTelefoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTelefone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTelefone))
            )
            .andExpect(status().isOk());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
        Telefone testTelefone = telefoneList.get(telefoneList.size() - 1);
        assertThat(testTelefone.getDdd()).isEqualTo(UPDATED_DDD);
        assertThat(testTelefone.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testTelefone.getTipoTelefone()).isEqualTo(UPDATED_TIPO_TELEFONE);
    }

    @Test
    @Transactional
    void patchNonExistingTelefone() throws Exception {
        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();
        telefone.setId(count.incrementAndGet());

        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTelefoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, telefoneDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(telefoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTelefone() throws Exception {
        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();
        telefone.setId(count.incrementAndGet());

        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelefoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(telefoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTelefone() throws Exception {
        int databaseSizeBeforeUpdate = telefoneRepository.findAll().size();
        telefone.setId(count.incrementAndGet());

        // Create the Telefone
        TelefoneDTO telefoneDTO = telefoneMapper.toDto(telefone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTelefoneMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(telefoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Telefone in the database
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTelefone() throws Exception {
        // Initialize the database
        telefoneRepository.saveAndFlush(telefone);

        int databaseSizeBeforeDelete = telefoneRepository.findAll().size();

        // Delete the telefone
        restTelefoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, telefone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Telefone> telefoneList = telefoneRepository.findAll();
        assertThat(telefoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
