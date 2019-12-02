import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';

@Component({
  selector: 'jhi-formulaire-evaluation-detail',
  templateUrl: './formulaire-evaluation-detail.component.html'
})
export class FormulaireEvaluationDetailComponent implements OnInit {
  formulaireEvaluation: IFormulaireEvaluation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ formulaireEvaluation }) => {
      this.formulaireEvaluation = formulaireEvaluation;
    });
  }

  previousState() {
    window.history.back();
  }
}
