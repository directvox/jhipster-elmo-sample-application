import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { FormulaireEvaluationService } from './formulaire-evaluation.service';

@Component({
  templateUrl: './formulaire-evaluation-delete-dialog.component.html'
})
export class FormulaireEvaluationDeleteDialogComponent {
  formulaireEvaluation: IFormulaireEvaluation;

  constructor(
    protected formulaireEvaluationService: FormulaireEvaluationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.formulaireEvaluationService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'formulaireEvaluationListModification',
        content: 'Deleted an formulaireEvaluation'
      });
      this.activeModal.dismiss(true);
    });
  }
}
