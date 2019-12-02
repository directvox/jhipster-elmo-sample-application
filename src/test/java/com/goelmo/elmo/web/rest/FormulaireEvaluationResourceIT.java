package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.FormulaireEvaluation;
import com.goelmo.elmo.repository.FormulaireEvaluationRepository;
import com.goelmo.elmo.service.FormulaireEvaluationService;
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
 * Integration tests for the {@link FormulaireEvaluationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class FormulaireEvaluationResourceIT {

    private static final String DEFAULT_TYPE_FORMULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FORMULAIRE = "BBBBBBBBBB";

    @Autowired
    private FormulaireEvaluationRepository formulaireEvaluationRepository;

    @Autowired
    private FormulaireEvaluationService formulaireEvaluationService;

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

    private MockMvc restFormulaireEvaluationMockMvc;

    private FormulaireEvaluation formulaireEvaluation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormulaireEvaluationResource formulaireEvaluationResource = new FormulaireEvaluationResource(formulaireEvaluationService);
        this.restFormulaireEvaluationMockMvc = MockMvcBuilders.standaloneSetup(formulaireEvaluationResource)
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
    public static FormulaireEvaluation createEntity(EntityManager em) {
        FormulaireEvaluation formulaireEvaluation = new FormulaireEvaluation()
            .typeFormulaire(DEFAULT_TYPE_FORMULAIRE);
        return formulaireEvaluation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormulaireEvaluation createUpdatedEntity(EntityManager em) {
        FormulaireEvaluation formulaireEvaluation = new FormulaireEvaluation()
            .typeFormulaire(UPDATED_TYPE_FORMULAIRE);
        return formulaireEvaluation;
    }

    @BeforeEach
    public void initTest() {
        formulaireEvaluation = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormulaireEvaluation() throws Exception {
        int databaseSizeBeforeCreate = formulaireEvaluationRepository.findAll().size();

        // Create the FormulaireEvaluation
        restFormulaireEvaluationMockMvc.perform(post("/api/formulaire-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireEvaluation)))
            .andExpect(status().isCreated());

        // Validate the FormulaireEvaluation in the database
        List<FormulaireEvaluation> formulaireEvaluationList = formulaireEvaluationRepository.findAll();
        assertThat(formulaireEvaluationList).hasSize(databaseSizeBeforeCreate + 1);
        FormulaireEvaluation testFormulaireEvaluation = formulaireEvaluationList.get(formulaireEvaluationList.size() - 1);
        assertThat(testFormulaireEvaluation.getTypeFormulaire()).isEqualTo(DEFAULT_TYPE_FORMULAIRE);
    }

    @Test
    @Transactional
    public void createFormulaireEvaluationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formulaireEvaluationRepository.findAll().size();

        // Create the FormulaireEvaluation with an existing ID
        formulaireEvaluation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormulaireEvaluationMockMvc.perform(post("/api/formulaire-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireEvaluation)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaireEvaluation in the database
        List<FormulaireEvaluation> formulaireEvaluationList = formulaireEvaluationRepository.findAll();
        assertThat(formulaireEvaluationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormulaireEvaluations() throws Exception {
        // Initialize the database
        formulaireEvaluationRepository.saveAndFlush(formulaireEvaluation);

        // Get all the formulaireEvaluationList
        restFormulaireEvaluationMockMvc.perform(get("/api/formulaire-evaluations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulaireEvaluation.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeFormulaire").value(hasItem(DEFAULT_TYPE_FORMULAIRE)));
    }
    
    @Test
    @Transactional
    public void getFormulaireEvaluation() throws Exception {
        // Initialize the database
        formulaireEvaluationRepository.saveAndFlush(formulaireEvaluation);

        // Get the formulaireEvaluation
        restFormulaireEvaluationMockMvc.perform(get("/api/formulaire-evaluations/{id}", formulaireEvaluation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formulaireEvaluation.getId().intValue()))
            .andExpect(jsonPath("$.typeFormulaire").value(DEFAULT_TYPE_FORMULAIRE));
    }

    @Test
    @Transactional
    public void getNonExistingFormulaireEvaluation() throws Exception {
        // Get the formulaireEvaluation
        restFormulaireEvaluationMockMvc.perform(get("/api/formulaire-evaluations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormulaireEvaluation() throws Exception {
        // Initialize the database
        formulaireEvaluationService.save(formulaireEvaluation);

        int databaseSizeBeforeUpdate = formulaireEvaluationRepository.findAll().size();

        // Update the formulaireEvaluation
        FormulaireEvaluation updatedFormulaireEvaluation = formulaireEvaluationRepository.findById(formulaireEvaluation.getId()).get();
        // Disconnect from session so that the updates on updatedFormulaireEvaluation are not directly saved in db
        em.detach(updatedFormulaireEvaluation);
        updatedFormulaireEvaluation
            .typeFormulaire(UPDATED_TYPE_FORMULAIRE);

        restFormulaireEvaluationMockMvc.perform(put("/api/formulaire-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormulaireEvaluation)))
            .andExpect(status().isOk());

        // Validate the FormulaireEvaluation in the database
        List<FormulaireEvaluation> formulaireEvaluationList = formulaireEvaluationRepository.findAll();
        assertThat(formulaireEvaluationList).hasSize(databaseSizeBeforeUpdate);
        FormulaireEvaluation testFormulaireEvaluation = formulaireEvaluationList.get(formulaireEvaluationList.size() - 1);
        assertThat(testFormulaireEvaluation.getTypeFormulaire()).isEqualTo(UPDATED_TYPE_FORMULAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingFormulaireEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = formulaireEvaluationRepository.findAll().size();

        // Create the FormulaireEvaluation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormulaireEvaluationMockMvc.perform(put("/api/formulaire-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireEvaluation)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaireEvaluation in the database
        List<FormulaireEvaluation> formulaireEvaluationList = formulaireEvaluationRepository.findAll();
        assertThat(formulaireEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormulaireEvaluation() throws Exception {
        // Initialize the database
        formulaireEvaluationService.save(formulaireEvaluation);

        int databaseSizeBeforeDelete = formulaireEvaluationRepository.findAll().size();

        // Delete the formulaireEvaluation
        restFormulaireEvaluationMockMvc.perform(delete("/api/formulaire-evaluations/{id}", formulaireEvaluation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormulaireEvaluation> formulaireEvaluationList = formulaireEvaluationRepository.findAll();
        assertThat(formulaireEvaluationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
