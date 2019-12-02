import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';
import { AttachementDemandeDeServiceService } from './attachement-demande-de-service.service';
import { AttachementDemandeDeServiceDeleteDialogComponent } from './attachement-demande-de-service-delete-dialog.component';

@Component({
  selector: 'jhi-attachement-demande-de-service',
  templateUrl: './attachement-demande-de-service.component.html'
})
export class AttachementDemandeDeServiceComponent implements OnInit, OnDestroy {
  attachementDemandeDeServices: IAttachementDemandeDeService[];
  eventSubscriber: Subscription;

  constructor(
    protected attachementDemandeDeServiceService: AttachementDemandeDeServiceService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.attachementDemandeDeServiceService.query().subscribe((res: HttpResponse<IAttachementDemandeDeService[]>) => {
      this.attachementDemandeDeServices = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInAttachementDemandeDeServices();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAttachementDemandeDeService) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInAttachementDemandeDeServices() {
    this.eventSubscriber = this.eventManager.subscribe('attachementDemandeDeServiceListModification', () => this.loadAll());
  }

  delete(attachementDemandeDeService: IAttachementDemandeDeService) {
    const modalRef = this.modalService.open(AttachementDemandeDeServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.attachementDemandeDeService = attachementDemandeDeService;
  }
}
