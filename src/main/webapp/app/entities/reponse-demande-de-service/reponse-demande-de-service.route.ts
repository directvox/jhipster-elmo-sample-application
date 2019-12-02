import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';
import { ReponseDemandeDeServiceService } from './reponse-demande-de-service.service';
import { ReponseDemandeDeServiceComponent } from './reponse-demande-de-service.component';
import { ReponseDemandeDeServiceDetailComponent } from './reponse-demande-de-service-detail.component';
import { ReponseDemandeDeServiceUpdateComponent } from './reponse-demande-de-service-update.component';
import { IReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';

@Injectable({ providedIn: 'root' })
export class ReponseDemandeDeServiceResolve implements Resolve<IReponseDemandeDeService> {
  constructor(private service: ReponseDemandeDeServiceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReponseDemandeDeService> {
    const id = route.params['id'];
    if (id) {
      return this.service
        .find(id)
        .pipe(map((reponseDemandeDeService: HttpResponse<ReponseDemandeDeService>) => reponseDemandeDeService.body));
    }
    return of(new ReponseDemandeDeService());
  }
}

export const reponseDemandeDeServiceRoute: Routes = [
  {
    path: '',
    component: ReponseDemandeDeServiceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.reponseDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReponseDemandeDeServiceDetailComponent,
    resolve: {
      reponseDemandeDeService: ReponseDemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.reponseDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReponseDemandeDeServiceUpdateComponent,
    resolve: {
      reponseDemandeDeService: ReponseDemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.reponseDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReponseDemandeDeServiceUpdateComponent,
    resolve: {
      reponseDemandeDeService: ReponseDemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.reponseDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
