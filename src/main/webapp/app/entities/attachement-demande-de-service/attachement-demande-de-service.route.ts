import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';
import { AttachementDemandeDeServiceService } from './attachement-demande-de-service.service';
import { AttachementDemandeDeServiceComponent } from './attachement-demande-de-service.component';
import { AttachementDemandeDeServiceDetailComponent } from './attachement-demande-de-service-detail.component';
import { AttachementDemandeDeServiceUpdateComponent } from './attachement-demande-de-service-update.component';
import { IAttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

@Injectable({ providedIn: 'root' })
export class AttachementDemandeDeServiceResolve implements Resolve<IAttachementDemandeDeService> {
  constructor(private service: AttachementDemandeDeServiceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAttachementDemandeDeService> {
    const id = route.params['id'];
    if (id) {
      return this.service
        .find(id)
        .pipe(map((attachementDemandeDeService: HttpResponse<AttachementDemandeDeService>) => attachementDemandeDeService.body));
    }
    return of(new AttachementDemandeDeService());
  }
}

export const attachementDemandeDeServiceRoute: Routes = [
  {
    path: '',
    component: AttachementDemandeDeServiceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.attachementDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AttachementDemandeDeServiceDetailComponent,
    resolve: {
      attachementDemandeDeService: AttachementDemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.attachementDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AttachementDemandeDeServiceUpdateComponent,
    resolve: {
      attachementDemandeDeService: AttachementDemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.attachementDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AttachementDemandeDeServiceUpdateComponent,
    resolve: {
      attachementDemandeDeService: AttachementDemandeDeServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.attachementDemandeDeService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
