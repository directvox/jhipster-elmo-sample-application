import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SpecClinique } from 'app/shared/model/spec-clinique.model';
import { SpecCliniqueService } from './spec-clinique.service';
import { SpecCliniqueComponent } from './spec-clinique.component';
import { SpecCliniqueDetailComponent } from './spec-clinique-detail.component';
import { SpecCliniqueUpdateComponent } from './spec-clinique-update.component';
import { ISpecClinique } from 'app/shared/model/spec-clinique.model';

@Injectable({ providedIn: 'root' })
export class SpecCliniqueResolve implements Resolve<ISpecClinique> {
  constructor(private service: SpecCliniqueService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpecClinique> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((specClinique: HttpResponse<SpecClinique>) => specClinique.body));
    }
    return of(new SpecClinique());
  }
}

export const specCliniqueRoute: Routes = [
  {
    path: '',
    component: SpecCliniqueComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.specClinique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SpecCliniqueDetailComponent,
    resolve: {
      specClinique: SpecCliniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.specClinique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SpecCliniqueUpdateComponent,
    resolve: {
      specClinique: SpecCliniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.specClinique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SpecCliniqueUpdateComponent,
    resolve: {
      specClinique: SpecCliniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.specClinique.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
