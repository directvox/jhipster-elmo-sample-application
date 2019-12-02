import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';
import { DemandeDeServiceService } from './demande-de-service.service';

@Component({
  templateUrl: './demande-de-service-delete-dialog.component.html'
})
export class DemandeDeServiceDeleteDialogComponent {
  demandeDeService: IDemandeDeService;

  constructor(
    protected demandeDeServiceService: DemandeDeServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.demandeDeServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'demandeDeServiceListModification',
        content: 'Deleted an demandeDeService'
      });
      this.activeModal.dismiss(true);
    });
  }
}
