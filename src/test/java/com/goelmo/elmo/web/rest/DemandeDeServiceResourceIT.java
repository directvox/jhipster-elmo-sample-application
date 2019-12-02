package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.DemandeDeService;
import com.goelmo.elmo.repository.DemandeDeServiceRepository;
import com.goelmo.elmo.service.DemandeDeServiceService;
import com.goelmo.elmo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;

import static com.goelmo.elmo.web.rest.TestUtil.sameInstant;
import static com.goelmo.elmo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.goelmo.elmo.domain.enumeration.StatutDemande;
import com.goelmo.elmo.domain.enumeration.Language;
import com.goelmo.elmo.domain.enumeration.Priorite;
import com.goelmo.elmo.domain.enumeration.Modalite;
import com.goelmo.elmo.domain.enumeration.TypeActivite;
/**
 * Integration tests for the {@link DemandeDeServiceResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class DemandeDeServiceResourceIT {

    private static final StatutDemande DEFAULT_STATUT = StatutDemande.ENCOURS;
    private static final StatutDemande UPDATED_STATUT = StatutDemande.TERMINE;

    private static final ZonedDateTime DEFAULT_DATE_CREATION_DEMANDE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATION_DEMANDE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Language DEFAULT_LANQUE = Language.FRANCAIS;
    private static final Language UPDATED_LANQUE = Language.ANGLAIS;

    private static final Priorite DEFAULT_PRIORITE = Priorite.URGENT;
    private static final Priorite UPDATED_PRIORITE = Priorite.IMPORTANT;

    private static final Modalite DEFAULT_MODALITE = Modalite.DIFERE;
    private static final Modalite UPDATED_MODALITE = Modalite.REEL;

    private static final TypeActivite DEFAULT_TYPE_ACTIVITE = TypeActivite.DISCUSSION;
    private static final TypeActivite UPDATED_TYPE_ACTIVITE = TypeActivite.DEMANDE_OPINION;

    @Autowired
    private DemandeDeServiceRepository demandeDeServiceRepository;

    @Mock
    private DemandeDeServiceRepository demandeDeServiceRepositoryMock;

    @Mock
    private DemandeDeServiceService demandeDeServiceServiceMock;

    @Autowired
    private DemandeDeServiceService demandeDeServiceService;

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

    private MockMvc restDemandeDeServiceMockMvc;

    private DemandeDeService demandeDeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DemandeDeServiceResource demandeDeServiceResource = new DemandeDeServiceResource(demandeDeServiceService);
        this.restDemandeDeServiceMockMvc = MockMvcBuilders.standaloneSetup(demandeDeServiceResource)
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
    public static DemandeDeService createEntity(EntityManager em) {
        DemandeDeService demandeDeService = new DemandeDeService()
            .statut(DEFAULT_STATUT)
            .dateCreationDemande(DEFAULT_DATE_CREATION_DEMANDE)
            .lanque(DEFAULT_LANQUE)
            .priorite(DEFAULT_PRIORITE)
            .modalite(DEFAULT_MODALITE)
            .typeActivite(DEFAULT_TYPE_ACTIVITE);
        return demandeDeService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeDeService createUpdatedEntity(EntityManager em) {
        DemandeDeService demandeDeService = new DemandeDeService()
            .statut(UPDATED_STATUT)
            .dateCreationDemande(UPDATED_DATE_CREATION_DEMANDE)
            .lanque(UPDATED_LANQUE)
            .priorite(UPDATED_PRIORITE)
            .modalite(UPDATED_MODALITE)
            .typeActivite(UPDATED_TYPE_ACTIVITE);
        return demandeDeService;
    }

    @BeforeEach
    public void initTest() {
        demandeDeService = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemandeDeService() throws Exception {
        int databaseSizeBeforeCreate = demandeDeServiceRepository.findAll().size();

        // Create the DemandeDeService
        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isCreated());

        // Validate the DemandeDeService in the database
        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeCreate + 1);
        DemandeDeService testDemandeDeService = demandeDeServiceList.get(demandeDeServiceList.size() - 1);
        assertThat(testDemandeDeService.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testDemandeDeService.getDateCreationDemande()).isEqualTo(DEFAULT_DATE_CREATION_DEMANDE);
        assertThat(testDemandeDeService.getLanque()).isEqualTo(DEFAULT_LANQUE);
        assertThat(testDemandeDeService.getPriorite()).isEqualTo(DEFAULT_PRIORITE);
        assertThat(testDemandeDeService.getModalite()).isEqualTo(DEFAULT_MODALITE);
        assertThat(testDemandeDeService.getTypeActivite()).isEqualTo(DEFAULT_TYPE_ACTIVITE);
    }

    @Test
    @Transactional
    public void createDemandeDeServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeDeServiceRepository.findAll().size();

        // Create the DemandeDeService with an existing ID
        demandeDeService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeDeService in the database
        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateCreationDemandeIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeDeServiceRepository.findAll().size();
        // set the field null
        demandeDeService.setDateCreationDemande(null);

        // Create the DemandeDeService, which fails.

        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLanqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeDeServiceRepository.findAll().size();
        // set the field null
        demandeDeService.setLanque(null);

        // Create the DemandeDeService, which fails.

        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrioriteIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeDeServiceRepository.findAll().size();
        // set the field null
        demandeDeService.setPriorite(null);

        // Create the DemandeDeService, which fails.

        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModaliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeDeServiceRepository.findAll().size();
        // set the field null
        demandeDeService.setModalite(null);

        // Create the DemandeDeService, which fails.

        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeActiviteIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeDeServiceRepository.findAll().size();
        // set the field null
        demandeDeService.setTypeActivite(null);

        // Create the DemandeDeService, which fails.

        restDemandeDeServiceMockMvc.perform(post("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDemandeDeServices() throws Exception {
        // Initialize the database
        demandeDeServiceRepository.saveAndFlush(demandeDeService);

        // Get all the demandeDeServiceList
        restDemandeDeServiceMockMvc.perform(get("/api/demande-de-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeDeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].dateCreationDemande").value(hasItem(sameInstant(DEFAULT_DATE_CREATION_DEMANDE))))
            .andExpect(jsonPath("$.[*].lanque").value(hasItem(DEFAULT_LANQUE.toString())))
            .andExpect(jsonPath("$.[*].priorite").value(hasItem(DEFAULT_PRIORITE.toString())))
            .andExpect(jsonPath("$.[*].modalite").value(hasItem(DEFAULT_MODALITE.toString())))
            .andExpect(jsonPath("$.[*].typeActivite").value(hasItem(DEFAULT_TYPE_ACTIVITE.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDemandeDeServicesWithEagerRelationshipsIsEnabled() throws Exception {
        DemandeDeServiceResource demandeDeServiceResource = new DemandeDeServiceResource(demandeDeServiceServiceMock);
        when(demandeDeServiceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restDemandeDeServiceMockMvc = MockMvcBuilders.standaloneSetup(demandeDeServiceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDemandeDeServiceMockMvc.perform(get("/api/demande-de-services?eagerload=true"))
        .andExpect(status().isOk());

        verify(demandeDeServiceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDemandeDeServicesWithEagerRelationshipsIsNotEnabled() throws Exception {
        DemandeDeServiceResource demandeDeServiceResource = new DemandeDeServiceResource(demandeDeServiceServiceMock);
            when(demandeDeServiceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restDemandeDeServiceMockMvc = MockMvcBuilders.standaloneSetup(demandeDeServiceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDemandeDeServiceMockMvc.perform(get("/api/demande-de-services?eagerload=true"))
        .andExpect(status().isOk());

            verify(demandeDeServiceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDemandeDeService() throws Exception {
        // Initialize the database
        demandeDeServiceRepository.saveAndFlush(demandeDeService);

        // Get the demandeDeService
        restDemandeDeServiceMockMvc.perform(get("/api/demande-de-services/{id}", demandeDeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demandeDeService.getId().intValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.dateCreationDemande").value(sameInstant(DEFAULT_DATE_CREATION_DEMANDE)))
            .andExpect(jsonPath("$.lanque").value(DEFAULT_LANQUE.toString()))
            .andExpect(jsonPath("$.priorite").value(DEFAULT_PRIORITE.toString()))
            .andExpect(jsonPath("$.modalite").value(DEFAULT_MODALITE.toString()))
            .andExpect(jsonPath("$.typeActivite").value(DEFAULT_TYPE_ACTIVITE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemandeDeService() throws Exception {
        // Get the demandeDeService
        restDemandeDeServiceMockMvc.perform(get("/api/demande-de-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemandeDeService() throws Exception {
        // Initialize the database
        demandeDeServiceService.save(demandeDeService);

        int databaseSizeBeforeUpdate = demandeDeServiceRepository.findAll().size();

        // Update the demandeDeService
        DemandeDeService updatedDemandeDeService = demandeDeServiceRepository.findById(demandeDeService.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeDeService are not directly saved in db
        em.detach(updatedDemandeDeService);
        updatedDemandeDeService
            .statut(UPDATED_STATUT)
            .dateCreationDemande(UPDATED_DATE_CREATION_DEMANDE)
            .lanque(UPDATED_LANQUE)
            .priorite(UPDATED_PRIORITE)
            .modalite(UPDATED_MODALITE)
            .typeActivite(UPDATED_TYPE_ACTIVITE);

        restDemandeDeServiceMockMvc.perform(put("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemandeDeService)))
            .andExpect(status().isOk());

        // Validate the DemandeDeService in the database
        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeUpdate);
        DemandeDeService testDemandeDeService = demandeDeServiceList.get(demandeDeServiceList.size() - 1);
        assertThat(testDemandeDeService.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testDemandeDeService.getDateCreationDemande()).isEqualTo(UPDATED_DATE_CREATION_DEMANDE);
        assertThat(testDemandeDeService.getLanque()).isEqualTo(UPDATED_LANQUE);
        assertThat(testDemandeDeService.getPriorite()).isEqualTo(UPDATED_PRIORITE);
        assertThat(testDemandeDeService.getModalite()).isEqualTo(UPDATED_MODALITE);
        assertThat(testDemandeDeService.getTypeActivite()).isEqualTo(UPDATED_TYPE_ACTIVITE);
    }

    @Test
    @Transactional
    public void updateNonExistingDemandeDeService() throws Exception {
        int databaseSizeBeforeUpdate = demandeDeServiceRepository.findAll().size();

        // Create the DemandeDeService

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeDeServiceMockMvc.perform(put("/api/demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDeService)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeDeService in the database
        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemandeDeService() throws Exception {
        // Initialize the database
        demandeDeServiceService.save(demandeDeService);

        int databaseSizeBeforeDelete = demandeDeServiceRepository.findAll().size();

        // Delete the demandeDeService
        restDemandeDeServiceMockMvc.perform(delete("/api/demande-de-services/{id}", demandeDeService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DemandeDeService> demandeDeServiceList = demandeDeServiceRepository.findAll();
        assertThat(demandeDeServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
