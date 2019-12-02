import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

@Component({
  selector: 'jhi-demande-de-service-detail',
  templateUrl: './demande-de-service-detail.component.html'
})
export class DemandeDeServiceDetailComponent implements OnInit {
  demandeDeService: IDemandeDeService;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ demandeDeService }) => {
      this.demandeDeService = demandeDeService;
    });
  }

  previousState() {
    window.history.back();
  }
}
