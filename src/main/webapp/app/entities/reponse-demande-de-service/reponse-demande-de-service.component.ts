import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';
import { ReponseDemandeDeServiceService } from './reponse-demande-de-service.service';
import { ReponseDemandeDeServiceDeleteDialogComponent } from './reponse-demande-de-service-delete-dialog.component';

@Component({
  selector: 'jhi-reponse-demande-de-service',
  templateUrl: './reponse-demande-de-service.component.html'
})
export class ReponseDemandeDeServiceComponent implements OnInit, OnDestroy {
  reponseDemandeDeServices: IReponseDemandeDeService[];
  eventSubscriber: Subscription;

  constructor(
    protected reponseDemandeDeServiceService: ReponseDemandeDeServiceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.reponseDemandeDeServiceService.query().subscribe((res: HttpResponse<IReponseDemandeDeService[]>) => {
      this.reponseDemandeDeServices = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInReponseDemandeDeServices();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IReponseDemandeDeService) {
    return item.id;
  }

  registerChangeInReponseDemandeDeServices() {
    this.eventSubscriber = this.eventManager.subscribe('reponseDemandeDeServiceListModification', () => this.loadAll());
  }

  delete(reponseDemandeDeService: IReponseDemandeDeService) {
    const modalRef = this.modalService.open(ReponseDemandeDeServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.reponseDemandeDeService = reponseDemandeDeService;
  }
}
