import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DemandeDeServiceService } from './demande-de-service.service';
import { DemandeDeServiceDeleteDialogComponent } from './demande-de-service-delete-dialog.component';

@Component({
  selector: 'jhi-demande-de-service',
  templateUrl: './demande-de-service.component.html'
})
export class DemandeDeServiceComponent implements OnInit, OnDestroy {
  demandeDeServices: IDemandeDeService[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected demandeDeServiceService: DemandeDeServiceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.demandeDeServices = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.demandeDeServiceService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IDemandeDeService[]>) => this.paginateDemandeDeServices(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.demandeDeServices = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDemandeDeServices();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDemandeDeService) {
    return item.id;
  }

  registerChangeInDemandeDeServices() {
    this.eventSubscriber = this.eventManager.subscribe('demandeDeServiceListModification', () => this.reset());
  }

  delete(demandeDeService: IDemandeDeService) {
    const modalRef = this.modalService.open(DemandeDeServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.demandeDeService = demandeDeService;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDemandeDeServices(data: IDemandeDeService[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.demandeDeServices.push(data[i]);
    }
  }
}
