import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { IntervenantComponent } from './intervenant.component';
import { IntervenantDetailComponent } from './intervenant-detail.component';
import { IntervenantUpdateComponent } from './intervenant-update.component';
import { IntervenantDeleteDialogComponent } from './intervenant-delete-dialog.component';
import { intervenantRoute } from './intervenant.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(intervenantRoute)],
  declarations: [IntervenantComponent, IntervenantDetailComponent, IntervenantUpdateComponent, IntervenantDeleteDialogComponent],
  entryComponents: [IntervenantDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationIntervenantModule {}
