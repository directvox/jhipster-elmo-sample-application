import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Intervenant } from 'app/shared/model/intervenant.model';
import { IntervenantService } from './intervenant.service';
import { IntervenantComponent } from './intervenant.component';
import { IntervenantDetailComponent } from './intervenant-detail.component';
import { IntervenantUpdateComponent } from './intervenant-update.component';
import { IIntervenant } from 'app/shared/model/intervenant.model';

@Injectable({ providedIn: 'root' })
export class IntervenantResolve implements Resolve<IIntervenant> {
  constructor(private service: IntervenantService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIntervenant> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((intervenant: HttpResponse<Intervenant>) => intervenant.body));
    }
    return of(new Intervenant());
  }
}

export const intervenantRoute: Routes = [
  {
    path: '',
    component: IntervenantComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.intervenant.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: IntervenantDetailComponent,
    resolve: {
      intervenant: IntervenantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.intervenant.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: IntervenantUpdateComponent,
    resolve: {
      intervenant: IntervenantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.intervenant.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: IntervenantUpdateComponent,
    resolve: {
      intervenant: IntervenantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.intervenant.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
