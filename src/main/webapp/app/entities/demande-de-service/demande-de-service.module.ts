import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { DemandeDeServiceComponent } from './demande-de-service.component';
import { DemandeDeServiceDetailComponent } from './demande-de-service-detail.component';
import { DemandeDeServiceUpdateComponent } from './demande-de-service-update.component';
import { DemandeDeServiceDeleteDialogComponent } from './demande-de-service-delete-dialog.component';
import { demandeDeServiceRoute } from './demande-de-service.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(demandeDeServiceRoute)],
  declarations: [
    DemandeDeServiceComponent,
    DemandeDeServiceDetailComponent,
    DemandeDeServiceUpdateComponent,
    DemandeDeServiceDeleteDialogComponent
  ],
  entryComponents: [DemandeDeServiceDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationDemandeDeServiceModule {}
