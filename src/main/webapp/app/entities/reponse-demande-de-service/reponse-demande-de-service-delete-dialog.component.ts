import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';
import { ReponseDemandeDeServiceService } from './reponse-demande-de-service.service';

@Component({
  templateUrl: './reponse-demande-de-service-delete-dialog.component.html'
})
export class ReponseDemandeDeServiceDeleteDialogComponent {
  reponseDemandeDeService: IReponseDemandeDeService;

  constructor(
    protected reponseDemandeDeServiceService: ReponseDemandeDeServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.reponseDemandeDeServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'reponseDemandeDeServiceListModification',
        content: 'Deleted an reponseDemandeDeService'
      });
      this.activeModal.dismiss(true);
    });
  }
}
