import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IFormulaireEvaluation, FormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { FormulaireEvaluationService } from './formulaire-evaluation.service';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';
import { DemandeDeServiceService } from 'app/entities/demande-de-service/demande-de-service.service';

@Component({
  selector: 'jhi-formulaire-evaluation-update',
  templateUrl: './formulaire-evaluation-update.component.html'
})
export class FormulaireEvaluationUpdateComponent implements OnInit {
  isSaving: boolean;

  demandedeservices: IDemandeDeService[];

  editForm = this.fb.group({
    id: [],
    typeFormulaire: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected formulaireEvaluationService: FormulaireEvaluationService,
    protected demandeDeServiceService: DemandeDeServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ formulaireEvaluation }) => {
      this.updateForm(formulaireEvaluation);
    });
    this.demandeDeServiceService
      .query()
      .subscribe(
        (res: HttpResponse<IDemandeDeService[]>) => (this.demandedeservices = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(formulaireEvaluation: IFormulaireEvaluation) {
    this.editForm.patchValue({
      id: formulaireEvaluation.id,
      typeFormulaire: formulaireEvaluation.typeFormulaire
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const formulaireEvaluation = this.createFromForm();
    if (formulaireEvaluation.id !== undefined) {
      this.subscribeToSaveResponse(this.formulaireEvaluationService.update(formulaireEvaluation));
    } else {
      this.subscribeToSaveResponse(this.formulaireEvaluationService.create(formulaireEvaluation));
    }
  }

  private createFromForm(): IFormulaireEvaluation {
    return {
      ...new FormulaireEvaluation(),
      id: this.editForm.get(['id']).value,
      typeFormulaire: this.editForm.get(['typeFormulaire']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormulaireEvaluation>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackDemandeDeServiceById(index: number, item: IDemandeDeService) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
