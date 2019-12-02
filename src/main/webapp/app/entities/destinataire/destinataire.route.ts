import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Destinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';
import { DestinataireComponent } from './destinataire.component';
import { DestinataireDetailComponent } from './destinataire-detail.component';
import { DestinataireUpdateComponent } from './destinataire-update.component';
import { IDestinataire } from 'app/shared/model/destinataire.model';

@Injectable({ providedIn: 'root' })
export class DestinataireResolve implements Resolve<IDestinataire> {
  constructor(private service: DestinataireService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDestinataire> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((destinataire: HttpResponse<Destinataire>) => destinataire.body));
    }
    return of(new Destinataire());
  }
}

export const destinataireRoute: Routes = [
  {
    path: '',
    component: DestinataireComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.destinataire.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DestinataireDetailComponent,
    resolve: {
      destinataire: DestinataireResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.destinataire.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DestinataireUpdateComponent,
    resolve: {
      destinataire: DestinataireResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.destinataire.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DestinataireUpdateComponent,
    resolve: {
      destinataire: DestinataireResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.destinataire.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
