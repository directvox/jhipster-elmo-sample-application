import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecClinique } from 'app/shared/model/spec-clinique.model';

@Component({
  selector: 'jhi-spec-clinique-detail',
  templateUrl: './spec-clinique-detail.component.html'
})
export class SpecCliniqueDetailComponent implements OnInit {
  specClinique: ISpecClinique;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ specClinique }) => {
      this.specClinique = specClinique;
    });
  }

  previousState() {
    window.history.back();
  }
}
