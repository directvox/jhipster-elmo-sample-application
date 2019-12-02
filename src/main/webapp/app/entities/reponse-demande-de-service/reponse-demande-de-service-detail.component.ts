import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';

@Component({
  selector: 'jhi-reponse-demande-de-service-detail',
  templateUrl: './reponse-demande-de-service-detail.component.html'
})
export class ReponseDemandeDeServiceDetailComponent implements OnInit {
  reponseDemandeDeService: IReponseDemandeDeService;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ reponseDemandeDeService }) => {
      this.reponseDemandeDeService = reponseDemandeDeService;
    });
  }

  previousState() {
    window.history.back();
  }
}
