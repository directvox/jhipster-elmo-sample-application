import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecClinique } from 'app/shared/model/spec-clinique.model';
import { SpecCliniqueService } from './spec-clinique.service';

@Component({
  templateUrl: './spec-clinique-delete-dialog.component.html'
})
export class SpecCliniqueDeleteDialogComponent {
  specClinique: ISpecClinique;

  constructor(
    protected specCliniqueService: SpecCliniqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.specCliniqueService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'specCliniqueListModification',
        content: 'Deleted an specClinique'
      });
      this.activeModal.dismiss(true);
    });
  }
}
