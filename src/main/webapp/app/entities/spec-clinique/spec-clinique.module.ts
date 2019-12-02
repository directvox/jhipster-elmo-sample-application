import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { SpecCliniqueComponent } from './spec-clinique.component';
import { SpecCliniqueDetailComponent } from './spec-clinique-detail.component';
import { SpecCliniqueUpdateComponent } from './spec-clinique-update.component';
import { SpecCliniqueDeleteDialogComponent } from './spec-clinique-delete-dialog.component';
import { specCliniqueRoute } from './spec-clinique.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(specCliniqueRoute)],
  declarations: [SpecCliniqueComponent, SpecCliniqueDetailComponent, SpecCliniqueUpdateComponent, SpecCliniqueDeleteDialogComponent],
  entryComponents: [SpecCliniqueDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationSpecCliniqueModule {}
