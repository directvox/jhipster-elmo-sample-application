package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.Intervenant;
import com.goelmo.elmo.repository.IntervenantRepository;
import com.goelmo.elmo.service.IntervenantService;
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
import java.util.List;

import static com.goelmo.elmo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link IntervenantResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class IntervenantResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private IntervenantRepository intervenantRepository;

    @Autowired
    private IntervenantService intervenantService;

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

    private MockMvc restIntervenantMockMvc;

    private Intervenant intervenant;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IntervenantResource intervenantResource = new IntervenantResource(intervenantService);
        this.restIntervenantMockMvc = MockMvcBuilders.standaloneSetup(intervenantResource)
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
    public static Intervenant createEntity(EntityManager em) {
        Intervenant intervenant = new Intervenant()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return intervenant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Intervenant createUpdatedEntity(EntityManager em) {
        Intervenant intervenant = new Intervenant()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return intervenant;
    }

    @BeforeEach
    public void initTest() {
        intervenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createIntervenant() throws Exception {
        int databaseSizeBeforeCreate = intervenantRepository.findAll().size();

        // Create the Intervenant
        restIntervenantMockMvc.perform(post("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(intervenant)))
            .andExpect(status().isCreated());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeCreate + 1);
        Intervenant testIntervenant = intervenantList.get(intervenantList.size() - 1);
        assertThat(testIntervenant.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testIntervenant.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testIntervenant.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testIntervenant.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createIntervenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = intervenantRepository.findAll().size();

        // Create the Intervenant with an existing ID
        intervenant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIntervenantMockMvc.perform(post("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(intervenant)))
            .andExpect(status().isBadRequest());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllIntervenants() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);

        // Get all the intervenantList
        restIntervenantMockMvc.perform(get("/api/intervenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(intervenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getIntervenant() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);

        // Get the intervenant
        restIntervenantMockMvc.perform(get("/api/intervenants/{id}", intervenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(intervenant.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingIntervenant() throws Exception {
        // Get the intervenant
        restIntervenantMockMvc.perform(get("/api/intervenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIntervenant() throws Exception {
        // Initialize the database
        intervenantService.save(intervenant);

        int databaseSizeBeforeUpdate = intervenantRepository.findAll().size();

        // Update the intervenant
        Intervenant updatedIntervenant = intervenantRepository.findById(intervenant.getId()).get();
        // Disconnect from session so that the updates on updatedIntervenant are not directly saved in db
        em.detach(updatedIntervenant);
        updatedIntervenant
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);

        restIntervenantMockMvc.perform(put("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIntervenant)))
            .andExpect(status().isOk());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeUpdate);
        Intervenant testIntervenant = intervenantList.get(intervenantList.size() - 1);
        assertThat(testIntervenant.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testIntervenant.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testIntervenant.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testIntervenant.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingIntervenant() throws Exception {
        int databaseSizeBeforeUpdate = intervenantRepository.findAll().size();

        // Create the Intervenant

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntervenantMockMvc.perform(put("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(intervenant)))
            .andExpect(status().isBadRequest());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIntervenant() throws Exception {
        // Initialize the database
        intervenantService.save(intervenant);

        int databaseSizeBeforeDelete = intervenantRepository.findAll().size();

        // Delete the intervenant
        restIntervenantMockMvc.perform(delete("/api/intervenants/{id}", intervenant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
