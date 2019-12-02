import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IDemandeDeService, DemandeDeService } from 'app/shared/model/demande-de-service.model';
import { DemandeDeServiceService } from './demande-de-service.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';
import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from 'app/entities/destinataire/destinataire.service';
import { ISpecClinique } from 'app/shared/model/spec-clinique.model';
import { SpecCliniqueService } from 'app/entities/spec-clinique/spec-clinique.service';
import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { FormulaireEvaluationService } from 'app/entities/formulaire-evaluation/formulaire-evaluation.service';

@Component({
  selector: 'jhi-demande-de-service-update',
  templateUrl: './demande-de-service-update.component.html'
})
export class DemandeDeServiceUpdateComponent implements OnInit {
  isSaving: boolean;

  patients: IPatient[];

  destinataires: IDestinataire[];

  specialitecliniques: ISpecClinique[];

  demandedeservices: IDemandeDeService[];

  formulaireevaluations: IFormulaireEvaluation[];

  editForm = this.fb.group({
    id: [],
    statut: [],
    dateCreationDemande: [null, [Validators.required]],
    lanque: [null, [Validators.required]],
    priorite: [null, [Validators.required]],
    modalite: [null, [Validators.required]],
    typeActivite: [null, [Validators.required]],
    patient: [],
    destinataire: [],
    specialiteClinique: [],
    ensembre: [],
    formulaireEvaluations: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected demandeDeServiceService: DemandeDeServiceService,
    protected patientService: PatientService,
    protected destinataireService: DestinataireService,
    protected specCliniqueService: SpecCliniqueService,
    protected formulaireEvaluationService: FormulaireEvaluationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ demandeDeService }) => {
      this.updateForm(demandeDeService);
    });
    this.patientService.query({ filter: 'demandedeservice-is-null' }).subscribe(
      (res: HttpResponse<IPatient[]>) => {
        if (!this.editForm.get('patient').value || !this.editForm.get('patient').value.id) {
          this.patients = res.body;
        } else {
          this.patientService
            .find(this.editForm.get('patient').value.id)
            .subscribe(
              (subRes: HttpResponse<IPatient>) => (this.patients = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.destinataireService.query({ filter: 'demandedeservice-is-null' }).subscribe(
      (res: HttpResponse<IDestinataire[]>) => {
        if (!this.editForm.get('destinataire').value || !this.editForm.get('destinataire').value.id) {
          this.destinataires = res.body;
        } else {
          this.destinataireService
            .find(this.editForm.get('destinataire').value.id)
            .subscribe(
              (subRes: HttpResponse<IDestinataire>) => (this.destinataires = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.specCliniqueService.query({ filter: 'demandedeservice-is-null' }).subscribe(
      (res: HttpResponse<ISpecClinique[]>) => {
        if (!this.editForm.get('specialiteClinique').value || !this.editForm.get('specialiteClinique').value.id) {
          this.specialitecliniques = res.body;
        } else {
          this.specCliniqueService
            .find(this.editForm.get('specialiteClinique').value.id)
            .subscribe(
              (subRes: HttpResponse<ISpecClinique>) => (this.specialitecliniques = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.demandeDeServiceService
      .query()
      .subscribe(
        (res: HttpResponse<IDemandeDeService[]>) => (this.demandedeservices = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.formulaireEvaluationService
      .query()
      .subscribe(
        (res: HttpResponse<IFormulaireEvaluation[]>) => (this.formulaireevaluations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(demandeDeService: IDemandeDeService) {
    this.editForm.patchValue({
      id: demandeDeService.id,
      statut: demandeDeService.statut,
      dateCreationDemande:
        demandeDeService.dateCreationDemande != null ? demandeDeService.dateCreationDemande.format(DATE_TIME_FORMAT) : null,
      lanque: demandeDeService.lanque,
      priorite: demandeDeService.priorite,
      modalite: demandeDeService.modalite,
      typeActivite: demandeDeService.typeActivite,
      patient: demandeDeService.patient,
      destinataire: demandeDeService.destinataire,
      specialiteClinique: demandeDeService.specialiteClinique,
      ensembre: demandeDeService.ensembre,
      formulaireEvaluations: demandeDeService.formulaireEvaluations
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const demandeDeService = this.createFromForm();
    if (demandeDeService.id !== undefined) {
      this.subscribeToSaveResponse(this.demandeDeServiceService.update(demandeDeService));
    } else {
      this.subscribeToSaveResponse(this.demandeDeServiceService.create(demandeDeService));
    }
  }

  private createFromForm(): IDemandeDeService {
    return {
      ...new DemandeDeService(),
      id: this.editForm.get(['id']).value,
      statut: this.editForm.get(['statut']).value,
      dateCreationDemande:
        this.editForm.get(['dateCreationDemande']).value != null
          ? moment(this.editForm.get(['dateCreationDemande']).value, DATE_TIME_FORMAT)
          : undefined,
      lanque: this.editForm.get(['lanque']).value,
      priorite: this.editForm.get(['priorite']).value,
      modalite: this.editForm.get(['modalite']).value,
      typeActivite: this.editForm.get(['typeActivite']).value,
      patient: this.editForm.get(['patient']).value,
      destinataire: this.editForm.get(['destinataire']).value,
      specialiteClinique: this.editForm.get(['specialiteClinique']).value,
      ensembre: this.editForm.get(['ensembre']).value,
      formulaireEvaluations: this.editForm.get(['formulaireEvaluations']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeDeService>>) {
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

  trackPatientById(index: number, item: IPatient) {
    return item.id;
  }

  trackDestinataireById(index: number, item: IDestinataire) {
    return item.id;
  }

  trackSpecCliniqueById(index: number, item: ISpecClinique) {
    return item.id;
  }

  trackDemandeDeServiceById(index: number, item: IDemandeDeService) {
    return item.id;
  }

  trackFormulaireEvaluationById(index: number, item: IFormulaireEvaluation) {
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
