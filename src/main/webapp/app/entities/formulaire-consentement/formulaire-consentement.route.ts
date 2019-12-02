import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';
import { FormulaireConsentementService } from './formulaire-consentement.service';
import { FormulaireConsentementComponent } from './formulaire-consentement.component';
import { FormulaireConsentementDetailComponent } from './formulaire-consentement-detail.component';
import { FormulaireConsentementUpdateComponent } from './formulaire-consentement-update.component';
import { IFormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

@Injectable({ providedIn: 'root' })
export class FormulaireConsentementResolve implements Resolve<IFormulaireConsentement> {
  constructor(private service: FormulaireConsentementService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormulaireConsentement> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((formulaireConsentement: HttpResponse<FormulaireConsentement>) => formulaireConsentement.body));
    }
    return of(new FormulaireConsentement());
  }
}

export const formulaireConsentementRoute: Routes = [
  {
    path: '',
    component: FormulaireConsentementComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireConsentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FormulaireConsentementDetailComponent,
    resolve: {
      formulaireConsentement: FormulaireConsentementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireConsentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FormulaireConsentementUpdateComponent,
    resolve: {
      formulaireConsentement: FormulaireConsentementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireConsentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FormulaireConsentementUpdateComponent,
    resolve: {
      formulaireConsentement: FormulaireConsentementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireConsentement.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
