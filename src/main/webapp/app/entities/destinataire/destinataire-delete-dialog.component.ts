import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';

@Component({
  templateUrl: './destinataire-delete-dialog.component.html'
})
export class DestinataireDeleteDialogComponent {
  destinataire: IDestinataire;

  constructor(
    protected destinataireService: DestinataireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.destinataireService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'destinataireListModification',
        content: 'Deleted an destinataire'
      });
      this.activeModal.dismiss(true);
    });
  }
}
