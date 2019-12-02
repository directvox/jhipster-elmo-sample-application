package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.Consentement;
import com.goelmo.elmo.repository.ConsentementRepository;
import com.goelmo.elmo.service.ConsentementService;
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
import org.springframework.util.Base64Utils;
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

import com.goelmo.elmo.domain.enumeration.TypeConsentement;
/**
 * Integration tests for the {@link ConsentementResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class ConsentementResourceIT {

    private static final TypeConsentement DEFAULT_TYPE_CONSENTEMENT = TypeConsentement.DEMANDE;
    private static final TypeConsentement UPDATED_TYPE_CONSENTEMENT = TypeConsentement.DSQ;

    private static final ZonedDateTime DEFAULT_DATE_CONSENTEMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CONSENTEMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_CONSENTEMENT_RECHERCHE = false;
    private static final Boolean UPDATED_CONSENTEMENT_RECHERCHE = true;

    private static final byte[] DEFAULT_CONSENTEMENT_PDF = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONSENTEMENT_PDF = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONSENTEMENT_PDF_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONSENTEMENT_PDF_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_DATE_SUPPRIMER_CONSENTEMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_SUPPRIMER_CONSENTEMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ConsentementRepository consentementRepository;

    @Autowired
    private ConsentementService consentementService;

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

    private MockMvc restConsentementMockMvc;

    private Consentement consentement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsentementResource consentementResource = new ConsentementResource(consentementService);
        this.restConsentementMockMvc = MockMvcBuilders.standaloneSetup(consentementResource)
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
    public static Consentement createEntity(EntityManager em) {
        Consentement consentement = new Consentement()
            .typeConsentement(DEFAULT_TYPE_CONSENTEMENT)
            .dateConsentement(DEFAULT_DATE_CONSENTEMENT)
            .consentementRecherche(DEFAULT_CONSENTEMENT_RECHERCHE)
            .consentementPDF(DEFAULT_CONSENTEMENT_PDF)
            .consentementPDFContentType(DEFAULT_CONSENTEMENT_PDF_CONTENT_TYPE)
            .dateSupprimerConsentement(DEFAULT_DATE_SUPPRIMER_CONSENTEMENT);
        return consentement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Consentement createUpdatedEntity(EntityManager em) {
        Consentement consentement = new Consentement()
            .typeConsentement(UPDATED_TYPE_CONSENTEMENT)
            .dateConsentement(UPDATED_DATE_CONSENTEMENT)
            .consentementRecherche(UPDATED_CONSENTEMENT_RECHERCHE)
            .consentementPDF(UPDATED_CONSENTEMENT_PDF)
            .consentementPDFContentType(UPDATED_CONSENTEMENT_PDF_CONTENT_TYPE)
            .dateSupprimerConsentement(UPDATED_DATE_SUPPRIMER_CONSENTEMENT);
        return consentement;
    }

    @BeforeEach
    public void initTest() {
        consentement = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsentement() throws Exception {
        int databaseSizeBeforeCreate = consentementRepository.findAll().size();

        // Create the Consentement
        restConsentementMockMvc.perform(post("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consentement)))
            .andExpect(status().isCreated());

        // Validate the Consentement in the database
        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeCreate + 1);
        Consentement testConsentement = consentementList.get(consentementList.size() - 1);
        assertThat(testConsentement.getTypeConsentement()).isEqualTo(DEFAULT_TYPE_CONSENTEMENT);
        assertThat(testConsentement.getDateConsentement()).isEqualTo(DEFAULT_DATE_CONSENTEMENT);
        assertThat(testConsentement.isConsentementRecherche()).isEqualTo(DEFAULT_CONSENTEMENT_RECHERCHE);
        assertThat(testConsentement.getConsentementPDF()).isEqualTo(DEFAULT_CONSENTEMENT_PDF);
        assertThat(testConsentement.getConsentementPDFContentType()).isEqualTo(DEFAULT_CONSENTEMENT_PDF_CONTENT_TYPE);
        assertThat(testConsentement.getDateSupprimerConsentement()).isEqualTo(DEFAULT_DATE_SUPPRIMER_CONSENTEMENT);
    }

    @Test
    @Transactional
    public void createConsentementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consentementRepository.findAll().size();

        // Create the Consentement with an existing ID
        consentement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsentementMockMvc.perform(post("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consentement)))
            .andExpect(status().isBadRequest());

        // Validate the Consentement in the database
        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeConsentementIsRequired() throws Exception {
        int databaseSizeBeforeTest = consentementRepository.findAll().size();
        // set the field null
        consentement.setTypeConsentement(null);

        // Create the Consentement, which fails.

        restConsentementMockMvc.perform(post("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consentement)))
            .andExpect(status().isBadRequest());

        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateConsentementIsRequired() throws Exception {
        int databaseSizeBeforeTest = consentementRepository.findAll().size();
        // set the field null
        consentement.setDateConsentement(null);

        // Create the Consentement, which fails.

        restConsentementMockMvc.perform(post("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consentement)))
            .andExpect(status().isBadRequest());

        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsentementRechercheIsRequired() throws Exception {
        int databaseSizeBeforeTest = consentementRepository.findAll().size();
        // set the field null
        consentement.setConsentementRecherche(null);

        // Create the Consentement, which fails.

        restConsentementMockMvc.perform(post("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consentement)))
            .andExpect(status().isBadRequest());

        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConsentements() throws Exception {
        // Initialize the database
        consentementRepository.saveAndFlush(consentement);

        // Get all the consentementList
        restConsentementMockMvc.perform(get("/api/consentements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consentement.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeConsentement").value(hasItem(DEFAULT_TYPE_CONSENTEMENT.toString())))
            .andExpect(jsonPath("$.[*].dateConsentement").value(hasItem(sameInstant(DEFAULT_DATE_CONSENTEMENT))))
            .andExpect(jsonPath("$.[*].consentementRecherche").value(hasItem(DEFAULT_CONSENTEMENT_RECHERCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].consentementPDFContentType").value(hasItem(DEFAULT_CONSENTEMENT_PDF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].consentementPDF").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONSENTEMENT_PDF))))
            .andExpect(jsonPath("$.[*].dateSupprimerConsentement").value(hasItem(sameInstant(DEFAULT_DATE_SUPPRIMER_CONSENTEMENT))));
    }
    
    @Test
    @Transactional
    public void getConsentement() throws Exception {
        // Initialize the database
        consentementRepository.saveAndFlush(consentement);

        // Get the consentement
        restConsentementMockMvc.perform(get("/api/consentements/{id}", consentement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consentement.getId().intValue()))
            .andExpect(jsonPath("$.typeConsentement").value(DEFAULT_TYPE_CONSENTEMENT.toString()))
            .andExpect(jsonPath("$.dateConsentement").value(sameInstant(DEFAULT_DATE_CONSENTEMENT)))
            .andExpect(jsonPath("$.consentementRecherche").value(DEFAULT_CONSENTEMENT_RECHERCHE.booleanValue()))
            .andExpect(jsonPath("$.consentementPDFContentType").value(DEFAULT_CONSENTEMENT_PDF_CONTENT_TYPE))
            .andExpect(jsonPath("$.consentementPDF").value(Base64Utils.encodeToString(DEFAULT_CONSENTEMENT_PDF)))
            .andExpect(jsonPath("$.dateSupprimerConsentement").value(sameInstant(DEFAULT_DATE_SUPPRIMER_CONSENTEMENT)));
    }

    @Test
    @Transactional
    public void getNonExistingConsentement() throws Exception {
        // Get the consentement
        restConsentementMockMvc.perform(get("/api/consentements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsentement() throws Exception {
        // Initialize the database
        consentementService.save(consentement);

        int databaseSizeBeforeUpdate = consentementRepository.findAll().size();

        // Update the consentement
        Consentement updatedConsentement = consentementRepository.findById(consentement.getId()).get();
        // Disconnect from session so that the updates on updatedConsentement are not directly saved in db
        em.detach(updatedConsentement);
        updatedConsentement
            .typeConsentement(UPDATED_TYPE_CONSENTEMENT)
            .dateConsentement(UPDATED_DATE_CONSENTEMENT)
            .consentementRecherche(UPDATED_CONSENTEMENT_RECHERCHE)
            .consentementPDF(UPDATED_CONSENTEMENT_PDF)
            .consentementPDFContentType(UPDATED_CONSENTEMENT_PDF_CONTENT_TYPE)
            .dateSupprimerConsentement(UPDATED_DATE_SUPPRIMER_CONSENTEMENT);

        restConsentementMockMvc.perform(put("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConsentement)))
            .andExpect(status().isOk());

        // Validate the Consentement in the database
        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeUpdate);
        Consentement testConsentement = consentementList.get(consentementList.size() - 1);
        assertThat(testConsentement.getTypeConsentement()).isEqualTo(UPDATED_TYPE_CONSENTEMENT);
        assertThat(testConsentement.getDateConsentement()).isEqualTo(UPDATED_DATE_CONSENTEMENT);
        assertThat(testConsentement.isConsentementRecherche()).isEqualTo(UPDATED_CONSENTEMENT_RECHERCHE);
        assertThat(testConsentement.getConsentementPDF()).isEqualTo(UPDATED_CONSENTEMENT_PDF);
        assertThat(testConsentement.getConsentementPDFContentType()).isEqualTo(UPDATED_CONSENTEMENT_PDF_CONTENT_TYPE);
        assertThat(testConsentement.getDateSupprimerConsentement()).isEqualTo(UPDATED_DATE_SUPPRIMER_CONSENTEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingConsentement() throws Exception {
        int databaseSizeBeforeUpdate = consentementRepository.findAll().size();

        // Create the Consentement

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConsentementMockMvc.perform(put("/api/consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consentement)))
            .andExpect(status().isBadRequest());

        // Validate the Consentement in the database
        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConsentement() throws Exception {
        // Initialize the database
        consentementService.save(consentement);

        int databaseSizeBeforeDelete = consentementRepository.findAll().size();

        // Delete the consentement
        restConsentementMockMvc.perform(delete("/api/consentements/{id}", consentement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Consentement> consentementList = consentementRepository.findAll();
        assertThat(consentementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
