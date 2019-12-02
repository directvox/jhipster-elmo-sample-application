package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.SpecClinique;
import com.goelmo.elmo.repository.SpecCliniqueRepository;
import com.goelmo.elmo.service.SpecCliniqueService;
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
 * Integration tests for the {@link SpecCliniqueResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class SpecCliniqueResourceIT {

    private static final String DEFAULT_NOM_SPECIALITE_CLINIQUE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SPECIALITE_CLINIQUE = "BBBBBBBBBB";

    @Autowired
    private SpecCliniqueRepository specCliniqueRepository;

    @Autowired
    private SpecCliniqueService specCliniqueService;

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

    private MockMvc restSpecCliniqueMockMvc;

    private SpecClinique specClinique;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpecCliniqueResource specCliniqueResource = new SpecCliniqueResource(specCliniqueService);
        this.restSpecCliniqueMockMvc = MockMvcBuilders.standaloneSetup(specCliniqueResource)
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
    public static SpecClinique createEntity(EntityManager em) {
        SpecClinique specClinique = new SpecClinique()
            .nomSpecialiteClinique(DEFAULT_NOM_SPECIALITE_CLINIQUE);
        return specClinique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpecClinique createUpdatedEntity(EntityManager em) {
        SpecClinique specClinique = new SpecClinique()
            .nomSpecialiteClinique(UPDATED_NOM_SPECIALITE_CLINIQUE);
        return specClinique;
    }

    @BeforeEach
    public void initTest() {
        specClinique = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecClinique() throws Exception {
        int databaseSizeBeforeCreate = specCliniqueRepository.findAll().size();

        // Create the SpecClinique
        restSpecCliniqueMockMvc.perform(post("/api/spec-cliniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specClinique)))
            .andExpect(status().isCreated());

        // Validate the SpecClinique in the database
        List<SpecClinique> specCliniqueList = specCliniqueRepository.findAll();
        assertThat(specCliniqueList).hasSize(databaseSizeBeforeCreate + 1);
        SpecClinique testSpecClinique = specCliniqueList.get(specCliniqueList.size() - 1);
        assertThat(testSpecClinique.getNomSpecialiteClinique()).isEqualTo(DEFAULT_NOM_SPECIALITE_CLINIQUE);
    }

    @Test
    @Transactional
    public void createSpecCliniqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specCliniqueRepository.findAll().size();

        // Create the SpecClinique with an existing ID
        specClinique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecCliniqueMockMvc.perform(post("/api/spec-cliniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specClinique)))
            .andExpect(status().isBadRequest());

        // Validate the SpecClinique in the database
        List<SpecClinique> specCliniqueList = specCliniqueRepository.findAll();
        assertThat(specCliniqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomSpecialiteCliniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = specCliniqueRepository.findAll().size();
        // set the field null
        specClinique.setNomSpecialiteClinique(null);

        // Create the SpecClinique, which fails.

        restSpecCliniqueMockMvc.perform(post("/api/spec-cliniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specClinique)))
            .andExpect(status().isBadRequest());

        List<SpecClinique> specCliniqueList = specCliniqueRepository.findAll();
        assertThat(specCliniqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpecCliniques() throws Exception {
        // Initialize the database
        specCliniqueRepository.saveAndFlush(specClinique);

        // Get all the specCliniqueList
        restSpecCliniqueMockMvc.perform(get("/api/spec-cliniques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specClinique.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomSpecialiteClinique").value(hasItem(DEFAULT_NOM_SPECIALITE_CLINIQUE)));
    }
    
    @Test
    @Transactional
    public void getSpecClinique() throws Exception {
        // Initialize the database
        specCliniqueRepository.saveAndFlush(specClinique);

        // Get the specClinique
        restSpecCliniqueMockMvc.perform(get("/api/spec-cliniques/{id}", specClinique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(specClinique.getId().intValue()))
            .andExpect(jsonPath("$.nomSpecialiteClinique").value(DEFAULT_NOM_SPECIALITE_CLINIQUE));
    }

    @Test
    @Transactional
    public void getNonExistingSpecClinique() throws Exception {
        // Get the specClinique
        restSpecCliniqueMockMvc.perform(get("/api/spec-cliniques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecClinique() throws Exception {
        // Initialize the database
        specCliniqueService.save(specClinique);

        int databaseSizeBeforeUpdate = specCliniqueRepository.findAll().size();

        // Update the specClinique
        SpecClinique updatedSpecClinique = specCliniqueRepository.findById(specClinique.getId()).get();
        // Disconnect from session so that the updates on updatedSpecClinique are not directly saved in db
        em.detach(updatedSpecClinique);
        updatedSpecClinique
            .nomSpecialiteClinique(UPDATED_NOM_SPECIALITE_CLINIQUE);

        restSpecCliniqueMockMvc.perform(put("/api/spec-cliniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpecClinique)))
            .andExpect(status().isOk());

        // Validate the SpecClinique in the database
        List<SpecClinique> specCliniqueList = specCliniqueRepository.findAll();
        assertThat(specCliniqueList).hasSize(databaseSizeBeforeUpdate);
        SpecClinique testSpecClinique = specCliniqueList.get(specCliniqueList.size() - 1);
        assertThat(testSpecClinique.getNomSpecialiteClinique()).isEqualTo(UPDATED_NOM_SPECIALITE_CLINIQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecClinique() throws Exception {
        int databaseSizeBeforeUpdate = specCliniqueRepository.findAll().size();

        // Create the SpecClinique

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecCliniqueMockMvc.perform(put("/api/spec-cliniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specClinique)))
            .andExpect(status().isBadRequest());

        // Validate the SpecClinique in the database
        List<SpecClinique> specCliniqueList = specCliniqueRepository.findAll();
        assertThat(specCliniqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecClinique() throws Exception {
        // Initialize the database
        specCliniqueService.save(specClinique);

        int databaseSizeBeforeDelete = specCliniqueRepository.findAll().size();

        // Delete the specClinique
        restSpecCliniqueMockMvc.perform(delete("/api/spec-cliniques/{id}", specClinique.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SpecClinique> specCliniqueList = specCliniqueRepository.findAll();
        assertThat(specCliniqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
