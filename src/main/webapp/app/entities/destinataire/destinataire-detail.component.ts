import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDestinataire } from 'app/shared/model/destinataire.model';

@Component({
  selector: 'jhi-destinataire-detail',
  templateUrl: './destinataire-detail.component.html'
})
export class DestinataireDetailComponent implements OnInit {
  destinataire: IDestinataire;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ destinataire }) => {
      this.destinataire = destinataire;
    });
  }

  previousState() {
    window.history.back();
  }
}
