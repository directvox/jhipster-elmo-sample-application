import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ReponseDemandeDeServiceComponent } from './reponse-demande-de-service.component';
import { ReponseDemandeDeServiceDetailComponent } from './reponse-demande-de-service-detail.component';
import { ReponseDemandeDeServiceUpdateComponent } from './reponse-demande-de-service-update.component';
import { ReponseDemandeDeServiceDeleteDialogComponent } from './reponse-demande-de-service-delete-dialog.component';
import { reponseDemandeDeServiceRoute } from './reponse-demande-de-service.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(reponseDemandeDeServiceRoute)],
  declarations: [
    ReponseDemandeDeServiceComponent,
    ReponseDemandeDeServiceDetailComponent,
    ReponseDemandeDeServiceUpdateComponent,
    ReponseDemandeDeServiceDeleteDialogComponent
  ],
  entryComponents: [ReponseDemandeDeServiceDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationReponseDemandeDeServiceModule {}
