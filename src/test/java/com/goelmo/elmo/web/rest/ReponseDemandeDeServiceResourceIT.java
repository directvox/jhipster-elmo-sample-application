package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.ReponseDemandeDeService;
import com.goelmo.elmo.repository.ReponseDemandeDeServiceRepository;
import com.goelmo.elmo.service.ReponseDemandeDeServiceService;
import com.goelmo.elmo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.goelmo.elmo.web.rest.TestUtil.sameInstant;
import static com.goelmo.elmo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ReponseDemandeDeServiceResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class ReponseDemandeDeServiceResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_REPONSE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_REPONSE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ReponseDemandeDeServiceRepository reponseDemandeDeServiceRepository;

    @Autowired
    private ReponseDemandeDeServiceService reponseDemandeDeServiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restReponseDemandeDeServiceMockMvc;

    private ReponseDemandeDeService reponseDemandeDeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReponseDemandeDeServiceResource reponseDemandeDeServiceResource = new ReponseDemandeDeServiceResource(reponseDemandeDeServiceService);
        this.restReponseDemandeDeServiceMockMvc = MockMvcBuilders.standaloneSetup(reponseDemandeDeServiceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReponseDemandeDeService createEntity(EntityManager em) {
        ReponseDemandeDeService reponseDemandeDeService = new ReponseDemandeDeService()
            .dateReponse(DEFAULT_DATE_REPONSE);
        return reponseDemandeDeService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReponseDemandeDeService createUpdatedEntity(EntityManager em) {
        ReponseDemandeDeService reponseDemandeDeService = new ReponseDemandeDeService()
            .dateReponse(UPDATED_DATE_REPONSE);
        return reponseDemandeDeService;
    }

    @BeforeEach
    public void initTest() {
        reponseDemandeDeService = createEntity(em);
    }

    @Test
    @Transactional
    public void createReponseDemandeDeService() throws Exception {
        int databaseSizeBeforeCreate = reponseDemandeDeServiceRepository.findAll().size();

        // Create the ReponseDemandeDeService
        restReponseDemandeDeServiceMockMvc.perform(post("/api/reponse-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponseDemandeDeService)))
            .andExpect(status().isCreated());

        // Validate the ReponseDemandeDeService in the database
        List<ReponseDemandeDeService> reponseDemandeDeServiceList = reponseDemandeDeServiceRepository.findAll();
        assertThat(reponseDemandeDeServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ReponseDemandeDeService testReponseDemandeDeService = reponseDemandeDeServiceList.get(reponseDemandeDeServiceList.size() - 1);
        assertThat(testReponseDemandeDeService.getDateReponse()).isEqualTo(DEFAULT_DATE_REPONSE);
    }

    @Test
    @Transactional
    public void createReponseDemandeDeServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reponseDemandeDeServiceRepository.findAll().size();

        // Create the ReponseDemandeDeService with an existing ID
        reponseDemandeDeService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReponseDemandeDeServiceMockMvc.perform(post("/api/reponse-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponseDemandeDeService)))
            .andExpect(status().isBadRequest());

        // Validate the ReponseDemandeDeService in the database
        List<ReponseDemandeDeService> reponseDemandeDeServiceList = reponseDemandeDeServiceRepository.findAll();
        assertThat(reponseDemandeDeServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateReponseIsRequired() throws Exception {
        int databaseSizeBeforeTest = reponseDemandeDeServiceRepository.findAll().size();
        // set the field null
        reponseDemandeDeService.setDateReponse(null);

        // Create the ReponseDemandeDeService, which fails.

        restReponseDemandeDeServiceMockMvc.perform(post("/api/reponse-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponseDemandeDeService)))
            .andExpect(status().isBadRequest());

        List<ReponseDemandeDeService> reponseDemandeDeServiceList = reponseDemandeDeServiceRepository.findAll();
        assertThat(reponseDemandeDeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReponseDemandeDeServices() throws Exception {
        // Initialize the database
        reponseDemandeDeServiceRepository.saveAndFlush(reponseDemandeDeService);

        // Get all the reponseDemandeDeServiceList
        restReponseDemandeDeServiceMockMvc.perform(get("/api/reponse-demande-de-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reponseDemandeDeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateReponse").value(hasItem(sameInstant(DEFAULT_DATE_REPONSE))));
    }
    
    @Test
    @Transactional
    public void getReponseDemandeDeService() throws Exception {
        // Initialize the database
        reponseDemandeDeServiceRepository.saveAndFlush(reponseDemandeDeService);

        // Get the reponseDemandeDeService
        restReponseDemandeDeServiceMockMvc.perform(get("/api/reponse-demande-de-services/{id}", reponseDemandeDeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reponseDemandeDeService.getId().intValue()))
            .andExpect(jsonPath("$.dateReponse").value(sameInstant(DEFAULT_DATE_REPONSE)));
    }

    @Test
    @Transactional
    public void getNonExistingReponseDemandeDeService() throws Exception {
        // Get the reponseDemandeDeService
        restReponseDemandeDeServiceMockMvc.perform(get("/api/reponse-demande-de-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReponseDemandeDeService() throws Exception {
        // Initialize the database
        reponseDemandeDeServiceService.save(reponseDemandeDeService);

        int databaseSizeBeforeUpdate = reponseDemandeDeServiceRepository.findAll().size();

        // Update the reponseDemandeDeService
        ReponseDemandeDeService updatedReponseDemandeDeService = reponseDemandeDeServiceRepository.findById(reponseDemandeDeService.getId()).get();
        // Disconnect from session so that the updates on updatedReponseDemandeDeService are not directly saved in db
        em.detach(updatedReponseDemandeDeService);
        updatedReponseDemandeDeService
            .dateReponse(UPDATED_DATE_REPONSE);

        restReponseDemandeDeServiceMockMvc.perform(put("/api/reponse-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReponseDemandeDeService)))
            .andExpect(status().isOk());

        // Validate the ReponseDemandeDeService in the database
        List<ReponseDemandeDeService> reponseDemandeDeServiceList = reponseDemandeDeServiceRepository.findAll();
        assertThat(reponseDemandeDeServiceList).hasSize(databaseSizeBeforeUpdate);
        ReponseDemandeDeService testReponseDemandeDeService = reponseDemandeDeServiceList.get(reponseDemandeDeServiceList.size() - 1);
        assertThat(testReponseDemandeDeService.getDateReponse()).isEqualTo(UPDATED_DATE_REPONSE);
    }

    @Test
    @Transactional
    public void updateNonExistingReponseDemandeDeService() throws Exception {
        int databaseSizeBeforeUpdate = reponseDemandeDeServiceRepository.findAll().size();

        // Create the ReponseDemandeDeService

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReponseDemandeDeServiceMockMvc.perform(put("/api/reponse-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reponseDemandeDeService)))
            .andExpect(status().isBadRequest());

        // Validate the ReponseDemandeDeService in the database
        List<ReponseDemandeDeService> reponseDemandeDeServiceList = reponseDemandeDeServiceRepository.findAll();
        assertThat(reponseDemandeDeServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReponseDemandeDeService() throws Exception {
        // Initialize the database
        reponseDemandeDeServiceService.save(reponseDemandeDeService);

        int databaseSizeBeforeDelete = reponseDemandeDeServiceRepository.findAll().size();

        // Delete the reponseDemandeDeService
        restReponseDemandeDeServiceMockMvc.perform(delete("/api/reponse-demande-de-services/{id}", reponseDemandeDeService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReponseDemandeDeService> reponseDemandeDeServiceList = reponseDemandeDeServiceRepository.findAll();
        assertThat(reponseDemandeDeServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
