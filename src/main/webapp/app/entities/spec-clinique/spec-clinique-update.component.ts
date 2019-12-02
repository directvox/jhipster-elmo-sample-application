import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ISpecClinique, SpecClinique } from 'app/shared/model/spec-clinique.model';
import { SpecCliniqueService } from './spec-clinique.service';
import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { FormulaireEvaluationService } from 'app/entities/formulaire-evaluation/formulaire-evaluation.service';
import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from 'app/entities/destinataire/destinataire.service';

@Component({
  selector: 'jhi-spec-clinique-update',
  templateUrl: './spec-clinique-update.component.html'
})
export class SpecCliniqueUpdateComponent implements OnInit {
  isSaving: boolean;

  formulaireevaluations: IFormulaireEvaluation[];

  destinataires: IDestinataire[];

  editForm = this.fb.group({
    id: [],
    nomSpecialiteClinique: [null, [Validators.required]],
    specialite: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected specCliniqueService: SpecCliniqueService,
    protected formulaireEvaluationService: FormulaireEvaluationService,
    protected destinataireService: DestinataireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ specClinique }) => {
      this.updateForm(specClinique);
    });
    this.formulaireEvaluationService
      .query()
      .subscribe(
        (res: HttpResponse<IFormulaireEvaluation[]>) => (this.formulaireevaluations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.destinataireService
      .query()
      .subscribe(
        (res: HttpResponse<IDestinataire[]>) => (this.destinataires = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(specClinique: ISpecClinique) {
    this.editForm.patchValue({
      id: specClinique.id,
      nomSpecialiteClinique: specClinique.nomSpecialiteClinique,
      specialite: specClinique.specialite
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const specClinique = this.createFromForm();
    if (specClinique.id !== undefined) {
      this.subscribeToSaveResponse(this.specCliniqueService.update(specClinique));
    } else {
      this.subscribeToSaveResponse(this.specCliniqueService.create(specClinique));
    }
  }

  private createFromForm(): ISpecClinique {
    return {
      ...new SpecClinique(),
      id: this.editForm.get(['id']).value,
      nomSpecialiteClinique: this.editForm.get(['nomSpecialiteClinique']).value,
      specialite: this.editForm.get(['specialite']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecClinique>>) {
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

  trackFormulaireEvaluationById(index: number, item: IFormulaireEvaluation) {
    return item.id;
  }

  trackDestinataireById(index: number, item: IDestinataire) {
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
