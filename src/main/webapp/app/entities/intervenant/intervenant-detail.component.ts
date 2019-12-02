import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIntervenant } from 'app/shared/model/intervenant.model';

@Component({
  selector: 'jhi-intervenant-detail',
  templateUrl: './intervenant-detail.component.html'
})
export class IntervenantDetailComponent implements OnInit {
  intervenant: IIntervenant;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ intervenant }) => {
      this.intervenant = intervenant;
    });
  }

  previousState() {
    window.history.back();
  }
}
