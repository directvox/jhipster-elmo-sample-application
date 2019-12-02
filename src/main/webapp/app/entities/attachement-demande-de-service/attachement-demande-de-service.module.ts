import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { AttachementDemandeDeServiceComponent } from './attachement-demande-de-service.component';
import { AttachementDemandeDeServiceDetailComponent } from './attachement-demande-de-service-detail.component';
import { AttachementDemandeDeServiceUpdateComponent } from './attachement-demande-de-service-update.component';
import { AttachementDemandeDeServiceDeleteDialogComponent } from './attachement-demande-de-service-delete-dialog.component';
import { attachementDemandeDeServiceRoute } from './attachement-demande-de-service.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(attachementDemandeDeServiceRoute)],
  declarations: [
    AttachementDemandeDeServiceComponent,
    AttachementDemandeDeServiceDetailComponent,
    AttachementDemandeDeServiceUpdateComponent,
    AttachementDemandeDeServiceDeleteDialogComponent
  ],
  entryComponents: [AttachementDemandeDeServiceDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationAttachementDemandeDeServiceModule {}
