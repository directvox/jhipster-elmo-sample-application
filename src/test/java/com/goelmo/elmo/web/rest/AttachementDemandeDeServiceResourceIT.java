package com.goelmo.elmo.web.rest;

import com.goelmo.elmo.JhipsterElmoSampleApplicationApp;
import com.goelmo.elmo.domain.AttachementDemandeDeService;
import com.goelmo.elmo.repository.AttachementDemandeDeServiceRepository;
import com.goelmo.elmo.service.AttachementDemandeDeServiceService;
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
import java.util.List;

import static com.goelmo.elmo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AttachementDemandeDeServiceResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElmoSampleApplicationApp.class)
public class AttachementDemandeDeServiceResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FICHIER = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FICHIER = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FICHIER_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FICHIER_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_REF_EXTERNE = "AAAAAAAAAA";
    private static final String UPDATED_REF_EXTERNE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private AttachementDemandeDeServiceRepository attachementDemandeDeServiceRepository;

    @Autowired
    private AttachementDemandeDeServiceService attachementDemandeDeServiceService;

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

    private MockMvc restAttachementDemandeDeServiceMockMvc;

    private AttachementDemandeDeService attachementDemandeDeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttachementDemandeDeServiceResource attachementDemandeDeServiceResource = new AttachementDemandeDeServiceResource(attachementDemandeDeServiceService);
        this.restAttachementDemandeDeServiceMockMvc = MockMvcBuilders.standaloneSetup(attachementDemandeDeServiceResource)
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
    public static AttachementDemandeDeService createEntity(EntityManager em) {
        AttachementDemandeDeService attachementDemandeDeService = new AttachementDemandeDeService()
            .nom(DEFAULT_NOM)
            .fichier(DEFAULT_FICHIER)
            .fichierContentType(DEFAULT_FICHIER_CONTENT_TYPE)
            .refExterne(DEFAULT_REF_EXTERNE)
            .note(DEFAULT_NOTE);
        return attachementDemandeDeService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttachementDemandeDeService createUpdatedEntity(EntityManager em) {
        AttachementDemandeDeService attachementDemandeDeService = new AttachementDemandeDeService()
            .nom(UPDATED_NOM)
            .fichier(UPDATED_FICHIER)
            .fichierContentType(UPDATED_FICHIER_CONTENT_TYPE)
            .refExterne(UPDATED_REF_EXTERNE)
            .note(UPDATED_NOTE);
        return attachementDemandeDeService;
    }

    @BeforeEach
    public void initTest() {
        attachementDemandeDeService = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttachementDemandeDeService() throws Exception {
        int databaseSizeBeforeCreate = attachementDemandeDeServiceRepository.findAll().size();

        // Create the AttachementDemandeDeService
        restAttachementDemandeDeServiceMockMvc.perform(post("/api/attachement-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachementDemandeDeService)))
            .andExpect(status().isCreated());

        // Validate the AttachementDemandeDeService in the database
        List<AttachementDemandeDeService> attachementDemandeDeServiceList = attachementDemandeDeServiceRepository.findAll();
        assertThat(attachementDemandeDeServiceList).hasSize(databaseSizeBeforeCreate + 1);
        AttachementDemandeDeService testAttachementDemandeDeService = attachementDemandeDeServiceList.get(attachementDemandeDeServiceList.size() - 1);
        assertThat(testAttachementDemandeDeService.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testAttachementDemandeDeService.getFichier()).isEqualTo(DEFAULT_FICHIER);
        assertThat(testAttachementDemandeDeService.getFichierContentType()).isEqualTo(DEFAULT_FICHIER_CONTENT_TYPE);
        assertThat(testAttachementDemandeDeService.getRefExterne()).isEqualTo(DEFAULT_REF_EXTERNE);
        assertThat(testAttachementDemandeDeService.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createAttachementDemandeDeServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attachementDemandeDeServiceRepository.findAll().size();

        // Create the AttachementDemandeDeService with an existing ID
        attachementDemandeDeService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttachementDemandeDeServiceMockMvc.perform(post("/api/attachement-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachementDemandeDeService)))
            .andExpect(status().isBadRequest());

        // Validate the AttachementDemandeDeService in the database
        List<AttachementDemandeDeService> attachementDemandeDeServiceList = attachementDemandeDeServiceRepository.findAll();
        assertThat(attachementDemandeDeServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAttachementDemandeDeServices() throws Exception {
        // Initialize the database
        attachementDemandeDeServiceRepository.saveAndFlush(attachementDemandeDeService);

        // Get all the attachementDemandeDeServiceList
        restAttachementDemandeDeServiceMockMvc.perform(get("/api/attachement-demande-de-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attachementDemandeDeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].fichierContentType").value(hasItem(DEFAULT_FICHIER_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fichier").value(hasItem(Base64Utils.encodeToString(DEFAULT_FICHIER))))
            .andExpect(jsonPath("$.[*].refExterne").value(hasItem(DEFAULT_REF_EXTERNE)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
    }
    
    @Test
    @Transactional
    public void getAttachementDemandeDeService() throws Exception {
        // Initialize the database
        attachementDemandeDeServiceRepository.saveAndFlush(attachementDemandeDeService);

        // Get the attachementDemandeDeService
        restAttachementDemandeDeServiceMockMvc.perform(get("/api/attachement-demande-de-services/{id}", attachementDemandeDeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attachementDemandeDeService.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.fichierContentType").value(DEFAULT_FICHIER_CONTENT_TYPE))
            .andExpect(jsonPath("$.fichier").value(Base64Utils.encodeToString(DEFAULT_FICHIER)))
            .andExpect(jsonPath("$.refExterne").value(DEFAULT_REF_EXTERNE))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAttachementDemandeDeService() throws Exception {
        // Get the attachementDemandeDeService
        restAttachementDemandeDeServiceMockMvc.perform(get("/api/attachement-demande-de-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttachementDemandeDeService() throws Exception {
        // Initialize the database
        attachementDemandeDeServiceService.save(attachementDemandeDeService);

        int databaseSizeBeforeUpdate = attachementDemandeDeServiceRepository.findAll().size();

        // Update the attachementDemandeDeService
        AttachementDemandeDeService updatedAttachementDemandeDeService = attachementDemandeDeServiceRepository.findById(attachementDemandeDeService.getId()).get();
        // Disconnect from session so that the updates on updatedAttachementDemandeDeService are not directly saved in db
        em.detach(updatedAttachementDemandeDeService);
        updatedAttachementDemandeDeService
            .nom(UPDATED_NOM)
            .fichier(UPDATED_FICHIER)
            .fichierContentType(UPDATED_FICHIER_CONTENT_TYPE)
            .refExterne(UPDATED_REF_EXTERNE)
            .note(UPDATED_NOTE);

        restAttachementDemandeDeServiceMockMvc.perform(put("/api/attachement-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttachementDemandeDeService)))
            .andExpect(status().isOk());

        // Validate the AttachementDemandeDeService in the database
        List<AttachementDemandeDeService> attachementDemandeDeServiceList = attachementDemandeDeServiceRepository.findAll();
        assertThat(attachementDemandeDeServiceList).hasSize(databaseSizeBeforeUpdate);
        AttachementDemandeDeService testAttachementDemandeDeService = attachementDemandeDeServiceList.get(attachementDemandeDeServiceList.size() - 1);
        assertThat(testAttachementDemandeDeService.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testAttachementDemandeDeService.getFichier()).isEqualTo(UPDATED_FICHIER);
        assertThat(testAttachementDemandeDeService.getFichierContentType()).isEqualTo(UPDATED_FICHIER_CONTENT_TYPE);
        assertThat(testAttachementDemandeDeService.getRefExterne()).isEqualTo(UPDATED_REF_EXTERNE);
        assertThat(testAttachementDemandeDeService.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingAttachementDemandeDeService() throws Exception {
        int databaseSizeBeforeUpdate = attachementDemandeDeServiceRepository.findAll().size();

        // Create the AttachementDemandeDeService

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttachementDemandeDeServiceMockMvc.perform(put("/api/attachement-demande-de-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attachementDemandeDeService)))
            .andExpect(status().isBadRequest());

        // Validate the AttachementDemandeDeService in the database
        List<AttachementDemandeDeService> attachementDemandeDeServiceList = attachementDemandeDeServiceRepository.findAll();
        assertThat(attachementDemandeDeServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttachementDemandeDeService() throws Exception {
        // Initialize the database
        attachementDemandeDeServiceService.save(attachementDemandeDeService);

        int databaseSizeBeforeDelete = attachementDemandeDeServiceRepository.findAll().size();

        // Delete the attachementDemandeDeService
        restAttachementDemandeDeServiceMockMvc.perform(delete("/api/attachement-demande-de-services/{id}", attachementDemandeDeService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AttachementDemandeDeService> attachementDemandeDeServiceList = attachementDemandeDeServiceRepository.findAll();
        assertThat(attachementDemandeDeServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
