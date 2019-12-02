import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterElmoSampleApplicationSharedModule } from 'app/shared/shared.module';
import { FormulaireConsentementComponent } from './formulaire-consentement.component';
import { FormulaireConsentementDetailComponent } from './formulaire-consentement-detail.component';
import { FormulaireConsentementUpdateComponent } from './formulaire-consentement-update.component';
import { FormulaireConsentementDeleteDialogComponent } from './formulaire-consentement-delete-dialog.component';
import { formulaireConsentementRoute } from './formulaire-consentement.route';

@NgModule({
  imports: [JhipsterElmoSampleApplicationSharedModule, RouterModule.forChild(formulaireConsentementRoute)],
  declarations: [
    FormulaireConsentementComponent,
    FormulaireConsentementDetailComponent,
    FormulaireConsentementUpdateComponent,
    FormulaireConsentementDeleteDialogComponent
  ],
  entryComponents: [FormulaireConsentementDeleteDialogComponent]
})
export class JhipsterElmoSampleApplicationFormulaireConsentementModule {}
