import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'spec-clinique',
        loadChildren: () => import('./spec-clinique/spec-clinique.module').then(m => m.JhipsterElmoSampleApplicationSpecCliniqueModule)
      },
      {
        path: 'demande-de-service',
        loadChildren: () =>
          import('./demande-de-service/demande-de-service.module').then(m => m.JhipsterElmoSampleApplicationDemandeDeServiceModule)
      },
      {
        path: 'attachement-demande-de-service',
        loadChildren: () =>
          import('./attachement-demande-de-service/attachement-demande-de-service.module').then(
            m => m.JhipsterElmoSampleApplicationAttachementDemandeDeServiceModule
          )
      },
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.JhipsterElmoSampleApplicationPatientModule)
      },
      {
        path: 'destinataire',
        loadChildren: () => import('./destinataire/destinataire.module').then(m => m.JhipsterElmoSampleApplicationDestinataireModule)
      },
      {
        path: 'intervenant',
        loadChildren: () => import('./intervenant/intervenant.module').then(m => m.JhipsterElmoSampleApplicationIntervenantModule)
      },
      {
        path: 'formulaire-evaluation',
        loadChildren: () =>
          import('./formulaire-evaluation/formulaire-evaluation.module').then(
            m => m.JhipsterElmoSampleApplicationFormulaireEvaluationModule
          )
      },
      {
        path: 'formulaire-consentement',
        loadChildren: () =>
          import('./formulaire-consentement/formulaire-consentement.module').then(
            m => m.JhipsterElmoSampleApplicationFormulaireConsentementModule
          )
      },
      {
        path: 'consentement',
        loadChildren: () => import('./consentement/consentement.module').then(m => m.JhipsterElmoSampleApplicationConsentementModule)
      },
      {
        path: 'reponse-demande-de-service',
        loadChildren: () =>
          import('./reponse-demande-de-service/reponse-demande-de-service.module').then(
            m => m.JhipsterElmoSampleApplicationReponseDemandeDeServiceModule
          )
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterElmoSampleApplicationEntityModule {}
