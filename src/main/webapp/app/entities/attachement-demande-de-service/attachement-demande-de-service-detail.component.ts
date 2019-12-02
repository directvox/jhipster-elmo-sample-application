import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

@Component({
  selector: 'jhi-attachement-demande-de-service-detail',
  templateUrl: './attachement-demande-de-service-detail.component.html'
})
export class AttachementDemandeDeServiceDetailComponent implements OnInit {
  attachementDemandeDeService: IAttachementDemandeDeService;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ attachementDemandeDeService }) => {
      this.attachementDemandeDeService = attachementDemandeDeService;
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
