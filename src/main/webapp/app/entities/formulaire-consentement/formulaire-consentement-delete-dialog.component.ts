import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';
import { FormulaireConsentementService } from './formulaire-consentement.service';

@Component({
  templateUrl: './formulaire-consentement-delete-dialog.component.html'
})
export class FormulaireConsentementDeleteDialogComponent {
  formulaireConsentement: IFormulaireConsentement;

  constructor(
    protected formulaireConsentementService: FormulaireConsentementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.formulaireConsentementService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'formulaireConsentementListModification',
        content: 'Deleted an formulaireConsentement'
      });
      this.activeModal.dismiss(true);
    });
  }
}
