import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDestinataire, Destinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';
import { IIntervenant } from 'app/shared/model/intervenant.model';
import { IntervenantService } from 'app/entities/intervenant/intervenant.service';
import { ISpecClinique } from 'app/shared/model/spec-clinique.model';
import { SpecCliniqueService } from 'app/entities/spec-clinique/spec-clinique.service';

@Component({
  selector: 'jhi-destinataire-update',
  templateUrl: './destinataire-update.component.html'
})
export class DestinataireUpdateComponent implements OnInit {
  isSaving: boolean;

  intervenants: IIntervenant[];

  speccliniques: ISpecClinique[];

  editForm = this.fb.group({
    id: [],
    nomDestinataire: [],
    ensembreDestinataire: [],
    specCliniques: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected destinataireService: DestinataireService,
    protected intervenantService: IntervenantService,
    protected specCliniqueService: SpecCliniqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ destinataire }) => {
      this.updateForm(destinataire);
    });
    this.intervenantService
      .query()
      .subscribe(
        (res: HttpResponse<IIntervenant[]>) => (this.intervenants = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.specCliniqueService
      .query()
      .subscribe(
        (res: HttpResponse<ISpecClinique[]>) => (this.speccliniques = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(destinataire: IDestinataire) {
    this.editForm.patchValue({
      id: destinataire.id,
      nomDestinataire: destinataire.nomDestinataire,
      ensembreDestinataire: destinataire.ensembreDestinataire,
      specCliniques: destinataire.specCliniques
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const destinataire = this.createFromForm();
    if (destinataire.id !== undefined) {
      this.subscribeToSaveResponse(this.destinataireService.update(destinataire));
    } else {
      this.subscribeToSaveResponse(this.destinataireService.create(destinataire));
    }
  }

  private createFromForm(): IDestinataire {
    return {
      ...new Destinataire(),
      id: this.editForm.get(['id']).value,
      nomDestinataire: this.editForm.get(['nomDestinataire']).value,
      ensembreDestinataire: this.editForm.get(['ensembreDestinataire']).value,
      specCliniques: this.editForm.get(['specCliniques']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDestinataire>>) {
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

  trackIntervenantById(index: number, item: IIntervenant) {
    return item.id;
  }

  trackSpecCliniqueById(index: number, item: ISpecClinique) {
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
