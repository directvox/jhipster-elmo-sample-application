import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISpecClinique } from 'app/shared/model/spec-clinique.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SpecCliniqueService } from './spec-clinique.service';
import { SpecCliniqueDeleteDialogComponent } from './spec-clinique-delete-dialog.component';

@Component({
  selector: 'jhi-spec-clinique',
  templateUrl: './spec-clinique.component.html'
})
export class SpecCliniqueComponent implements OnInit, OnDestroy {
  specCliniques: ISpecClinique[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected specCliniqueService: SpecCliniqueService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.specCliniques = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.specCliniqueService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ISpecClinique[]>) => this.paginateSpecCliniques(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.specCliniques = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInSpecCliniques();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISpecClinique) {
    return item.id;
  }

  registerChangeInSpecCliniques() {
    this.eventSubscriber = this.eventManager.subscribe('specCliniqueListModification', () => this.reset());
  }

  delete(specClinique: ISpecClinique) {
    const modalRef = this.modalService.open(SpecCliniqueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.specClinique = specClinique;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSpecCliniques(data: ISpecClinique[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.specCliniques.push(data[i]);
    }
  }
}
