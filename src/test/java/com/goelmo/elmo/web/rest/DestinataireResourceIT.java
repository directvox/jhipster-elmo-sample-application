package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.Destinataire;
import com.goelmo.elmo.repository.DestinataireRepository;
import com.goelmo.elmo.service.DestinataireService;
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
import java.util.ArrayList;
import java.util.List;

import static com.goelmo.elmo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DestinataireResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class DestinataireResourceIT {

    private static final String DEFAULT_NOM_DESTINATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_DESTINATAIRE = "BBBBBBBBBB";

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Mock
    private DestinataireRepository destinataireRepositoryMock;

    @Mock
    private DestinataireService destinataireServiceMock;

    @Autowired
    private DestinataireService destinataireService;

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

    private MockMvc restDestinataireMockMvc;

    private Destinataire destinataire;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DestinataireResource destinataireResource = new DestinataireResource(destinataireService);
        this.restDestinataireMockMvc = MockMvcBuilders.standaloneSetup(destinataireResource)
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
    public static Destinataire createEntity(EntityManager em) {
        Destinataire destinataire = new Destinataire()
            .nomDestinataire(DEFAULT_NOM_DESTINATAIRE);
        return destinataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Destinataire createUpdatedEntity(EntityManager em) {
        Destinataire destinataire = new Destinataire()
            .nomDestinataire(UPDATED_NOM_DESTINATAIRE);
        return destinataire;
    }

    @BeforeEach
    public void initTest() {
        destinataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createDestinataire() throws Exception {
        int databaseSizeBeforeCreate = destinataireRepository.findAll().size();

        // Create the Destinataire
        restDestinataireMockMvc.perform(post("/api/destinataires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(destinataire)))
            .andExpect(status().isCreated());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeCreate + 1);
        Destinataire testDestinataire = destinataireList.get(destinataireList.size() - 1);
        assertThat(testDestinataire.getNomDestinataire()).isEqualTo(DEFAULT_NOM_DESTINATAIRE);
    }

    @Test
    @Transactional
    public void createDestinataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = destinataireRepository.findAll().size();

        // Create the Destinataire with an existing ID
        destinataire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDestinataireMockMvc.perform(post("/api/destinataires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(destinataire)))
            .andExpect(status().isBadRequest());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDestinataires() throws Exception {
        // Initialize the database
        destinataireRepository.saveAndFlush(destinataire);

        // Get all the destinataireList
        restDestinataireMockMvc.perform(get("/api/destinataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(destinataire.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomDestinataire").value(hasItem(DEFAULT_NOM_DESTINATAIRE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDestinatairesWithEagerRelationshipsIsEnabled() throws Exception {
        DestinataireResource destinataireResource = new DestinataireResource(destinataireServiceMock);
        when(destinataireServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restDestinataireMockMvc = MockMvcBuilders.standaloneSetup(destinataireResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDestinataireMockMvc.perform(get("/api/destinataires?eagerload=true"))
        .andExpect(status().isOk());

        verify(destinataireServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDestinatairesWithEagerRelationshipsIsNotEnabled() throws Exception {
        DestinataireResource destinataireResource = new DestinataireResource(destinataireServiceMock);
            when(destinataireServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restDestinataireMockMvc = MockMvcBuilders.standaloneSetup(destinataireResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDestinataireMockMvc.perform(get("/api/destinataires?eagerload=true"))
        .andExpect(status().isOk());

            verify(destinataireServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDestinataire() throws Exception {
        // Initialize the database
        destinataireRepository.saveAndFlush(destinataire);

        // Get the destinataire
        restDestinataireMockMvc.perform(get("/api/destinataires/{id}", destinataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(destinataire.getId().intValue()))
            .andExpect(jsonPath("$.nomDestinataire").value(DEFAULT_NOM_DESTINATAIRE));
    }

    @Test
    @Transactional
    public void getNonExistingDestinataire() throws Exception {
        // Get the destinataire
        restDestinataireMockMvc.perform(get("/api/destinataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDestinataire() throws Exception {
        // Initialize the database
        destinataireService.save(destinataire);

        int databaseSizeBeforeUpdate = destinataireRepository.findAll().size();

        // Update the destinataire
        Destinataire updatedDestinataire = destinataireRepository.findById(destinataire.getId()).get();
        // Disconnect from session so that the updates on updatedDestinataire are not directly saved in db
        em.detach(updatedDestinataire);
        updatedDestinataire
            .nomDestinataire(UPDATED_NOM_DESTINATAIRE);

        restDestinataireMockMvc.perform(put("/api/destinataires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDestinataire)))
            .andExpect(status().isOk());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeUpdate);
        Destinataire testDestinataire = destinataireList.get(destinataireList.size() - 1);
        assertThat(testDestinataire.getNomDestinataire()).isEqualTo(UPDATED_NOM_DESTINATAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingDestinataire() throws Exception {
        int databaseSizeBeforeUpdate = destinataireRepository.findAll().size();

        // Create the Destinataire

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDestinataireMockMvc.perform(put("/api/destinataires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(destinataire)))
            .andExpect(status().isBadRequest());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDestinataire() throws Exception {
        // Initialize the database
        destinataireService.save(destinataire);

        int databaseSizeBeforeDelete = destinataireRepository.findAll().size();

        // Delete the destinataire
        restDestinataireMockMvc.perform(delete("/api/destinataires/{id}", destinataire.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
