import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Consentement } from 'app/shared/model/consentement.model';
import { ConsentementService } from './consentement.service';
import { ConsentementComponent } from './consentement.component';
import { ConsentementDetailComponent } from './consentement-detail.component';
import { ConsentementUpdateComponent } from './consentement-update.component';
import { IConsentement } from 'app/shared/model/consentement.model';

@Injectable({ providedIn: 'root' })
export class ConsentementResolve implements Resolve<IConsentement> {
  constructor(private service: ConsentementService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConsentement> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((consentement: HttpResponse<Consentement>) => consentement.body));
    }
    return of(new Consentement());
  }
}

export const consentementRoute: Routes = [
  {
    path: '',
    component: ConsentementComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.consentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ConsentementDetailComponent,
    resolve: {
      consentement: ConsentementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.consentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ConsentementUpdateComponent,
    resolve: {
      consentement: ConsentementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.consentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConsentementUpdateComponent,
    resolve: {
      consentement: ConsentementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.consentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
