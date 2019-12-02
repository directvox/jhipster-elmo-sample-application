package com.goelmo.elmo.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.goelmo.elmo.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.goelmo.elmo.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.goelmo.elmo.domain.User.class.getName());
            createCache(cm, com.goelmo.elmo.domain.Authority.class.getName());
            createCache(cm, com.goelmo.elmo.domain.User.class.getName() + ".authorities");
            createCache(cm, com.goelmo.elmo.domain.SpecClinique.class.getName());
            createCache(cm, com.goelmo.elmo.domain.SpecClinique.class.getName() + ".destinataires");
            createCache(cm, com.goelmo.elmo.domain.DemandeDeService.class.getName());
            createCache(cm, com.goelmo.elmo.domain.DemandeDeService.class.getName() + ".formulaireEvaluations");
            createCache(cm, com.goelmo.elmo.domain.AttachementDemandeDeService.class.getName());
            createCache(cm, com.goelmo.elmo.domain.Patient.class.getName());
            createCache(cm, com.goelmo.elmo.domain.Destinataire.class.getName());
            createCache(cm, com.goelmo.elmo.domain.Destinataire.class.getName() + ".specCliniques");
            createCache(cm, com.goelmo.elmo.domain.Intervenant.class.getName());
            createCache(cm, com.goelmo.elmo.domain.FormulaireEvaluation.class.getName());
            createCache(cm, com.goelmo.elmo.domain.FormulaireEvaluation.class.getName() + ".demandeDeServices");
            createCache(cm, com.goelmo.elmo.domain.FormulaireConsentement.class.getName());
            createCache(cm, com.goelmo.elmo.domain.Consentement.class.getName());
            createCache(cm, com.goelmo.elmo.domain.ReponseDemandeDeService.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
