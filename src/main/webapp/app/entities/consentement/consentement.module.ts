import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ConsentementComponent } from './consentement.component';
import { ConsentementDetailComponent } from './consentement-detail.component';
import { ConsentementUpdateComponent } from './consentement-update.component';
import { ConsentementDeleteDialogComponent } from './consentement-delete-dialog.component';
import { consentementRoute } from './consentement.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(consentementRoute)],
  declarations: [ConsentementComponent, ConsentementDetailComponent, ConsentementUpdateComponent, ConsentementDeleteDialogComponent],
  entryComponents: [ConsentementDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationConsentementModule {}
