import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsentement } from 'app/shared/model/consentement.model';
import { ConsentementService } from './consentement.service';

@Component({
  templateUrl: './consentement-delete-dialog.component.html'
})
export class ConsentementDeleteDialogComponent {
  consentement: IConsentement;

  constructor(
    protected consentementService: ConsentementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.consentementService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'consentementListModification',
        content: 'Deleted an consentement'
      });
      this.activeModal.dismiss(true);
    });
  }
}
