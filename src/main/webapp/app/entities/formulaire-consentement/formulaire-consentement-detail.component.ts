import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

@Component({
  selector: 'jhi-formulaire-consentement-detail',
  templateUrl: './formulaire-consentement-detail.component.html'
})
export class FormulaireConsentementDetailComponent implements OnInit {
  formulaireConsentement: IFormulaireConsentement;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ formulaireConsentement }) => {
      this.formulaireConsentement = formulaireConsentement;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
