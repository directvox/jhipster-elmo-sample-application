import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIntervenant } from 'app/shared/model/intervenant.model';
import { IntervenantService } from './intervenant.service';

@Component({
  templateUrl: './intervenant-delete-dialog.component.html'
})
export class IntervenantDeleteDialogComponent {
  intervenant: IIntervenant;

  constructor(
    protected intervenantService: IntervenantService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.intervenantService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'intervenantListModification',
        content: 'Deleted an intervenant'
      });
      this.activeModal.dismiss(true);
    });
  }
}
