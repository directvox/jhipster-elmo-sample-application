import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { FormulaireEvaluationService } from './formulaire-evaluation.service';
import { FormulaireEvaluationComponent } from './formulaire-evaluation.component';
import { FormulaireEvaluationDetailComponent } from './formulaire-evaluation-detail.component';
import { FormulaireEvaluationUpdateComponent } from './formulaire-evaluation-update.component';
import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';

@Injectable({ providedIn: 'root' })
export class FormulaireEvaluationResolve implements Resolve<IFormulaireEvaluation> {
  constructor(private service: FormulaireEvaluationService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormulaireEvaluation> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((formulaireEvaluation: HttpResponse<FormulaireEvaluation>) => formulaireEvaluation.body));
    }
    return of(new FormulaireEvaluation());
  }
}

export const formulaireEvaluationRoute: Routes = [
  {
    path: '',
    component: FormulaireEvaluationComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FormulaireEvaluationDetailComponent,
    resolve: {
      formulaireEvaluation: FormulaireEvaluationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FormulaireEvaluationUpdateComponent,
    resolve: {
      formulaireEvaluation: FormulaireEvaluationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FormulaireEvaluationUpdateComponent,
    resolve: {
      formulaireEvaluation: FormulaireEvaluationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterElmoSampleApplicationApp.formulaireEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
