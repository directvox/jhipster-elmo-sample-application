import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';
import { AttachementDemandeDeServiceService } from './attachement-demande-de-service.service';

@Component({
  templateUrl: './attachement-demande-de-service-delete-dialog.component.html'
})
export class AttachementDemandeDeServiceDeleteDialogComponent {
  attachementDemandeDeService: IAttachementDemandeDeService;

  constructor(
    protected attachementDemandeDeServiceService: AttachementDemandeDeServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.attachementDemandeDeServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'attachementDemandeDeServiceListModification',
        content: 'Deleted an attachementDemandeDeService'
      });
      this.activeModal.dismiss(true);
    });
  }
}
