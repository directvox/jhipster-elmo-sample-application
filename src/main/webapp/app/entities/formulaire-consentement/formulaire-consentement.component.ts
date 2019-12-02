import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';
import { FormulaireConsentementService } from './formulaire-consentement.service';
import { FormulaireConsentementDeleteDialogComponent } from './formulaire-consentement-delete-dialog.component';

@Component({
  selector: 'jhi-formulaire-consentement',
  templateUrl: './formulaire-consentement.component.html'
})
export class FormulaireConsentementComponent implements OnInit, OnDestroy {
  formulaireConsentements: IFormulaireConsentement[];
  eventSubscriber: Subscription;

  constructor(
    protected formulaireConsentementService: FormulaireConsentementService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.formulaireConsentementService.query().subscribe((res: HttpResponse<IFormulaireConsentement[]>) => {
      this.formulaireConsentements = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInFormulaireConsentements();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFormulaireConsentement) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInFormulaireConsentements() {
    this.eventSubscriber = this.eventManager.subscribe('formulaireConsentementListModification', () => this.loadAll());
  }

  delete(formulaireConsentement: IFormulaireConsentement) {
    const modalRef = this.modalService.open(FormulaireConsentementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formulaireConsentement = formulaireConsentement;
  }
}
