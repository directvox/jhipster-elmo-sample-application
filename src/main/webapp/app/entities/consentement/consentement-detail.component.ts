import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IConsentement } from 'app/shared/model/consentement.model';

@Component({
  selector: 'jhi-consentement-detail',
  templateUrl: './consentement-detail.component.html'
})
export class ConsentementDetailComponent implements OnInit {
  consentement: IConsentement;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ consentement }) => {
      this.consentement = consentement;
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
