import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DemandeDeService } from 'app/shared/model/demande-de-service.model';
import { DemandeDeServiceService } from './demande-de-service.service';
import { DemandeDeServiceComponent } from './demande-de-service.component';
import { DemandeDeServiceDetailComponent } from './demande-de-service-detail.component';
import { DemandeDeServiceUpdateComponent } from './demande-de-service-update.component';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

@Injectable({ providedIn: 'root' })
export class DemandeDeServiceResolve implements Resolve<IDemandeDeService> {
  constructor(private service: DemandeDeServiceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDemandeDeService> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((demandeDeService: HttpResponse<DemandeDeService>) => demandeDeService.body));
    }
    return of(new DemandeDeService());
  }
}

export const demandeDeServiceRoute: Routes = [
  {
    path: '',
    component: DemandeDeServiceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.demandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DemandeDeServiceDetailComponent,
    resolve: {
      demandeDeService: DemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.demandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DemandeDeServiceUpdateComponent,
    resolve: {
      demandeDeService: DemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.demandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DemandeDeServiceUpdateComponent,
    resolve: {
      demandeDeService: DemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.demandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
