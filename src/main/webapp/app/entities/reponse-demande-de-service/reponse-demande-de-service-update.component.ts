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
import { IReponseDemandeDeService, ReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';
import { ReponseDemandeDeServiceService } from './reponse-demande-de-service.service';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';
import { DemandeDeServiceService } from 'app/entities/demande-de-service/demande-de-service.service';

@Component({
  selector: 'jhi-reponse-demande-de-service-update',
  templateUrl: './reponse-demande-de-service-update.component.html'
})
export class ReponseDemandeDeServiceUpdateComponent implements OnInit {
  isSaving: boolean;

  demandedeservices: IDemandeDeService[];

  editForm = this.fb.group({
    id: [],
    dateReponse: [null, [Validators.required]],
    demandeDeService: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected reponseDemandeDeServiceService: ReponseDemandeDeServiceService,
    protected demandeDeServiceService: DemandeDeServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ reponseDemandeDeService }) => {
      this.updateForm(reponseDemandeDeService);
    });
    this.demandeDeServiceService.query({ filter: 'reponsedemandedeservice-is-null' }).subscribe(
      (res: HttpResponse<IDemandeDeService[]>) => {
        if (!this.editForm.get('demandeDeService').value || !this.editForm.get('demandeDeService').value.id) {
          this.demandedeservices = res.body;
        } else {
          this.demandeDeServiceService
            .find(this.editForm.get('demandeDeService').value.id)
            .subscribe(
              (subRes: HttpResponse<IDemandeDeService>) => (this.demandedeservices = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(reponseDemandeDeService: IReponseDemandeDeService) {
    this.editForm.patchValue({
      id: reponseDemandeDeService.id,
      dateReponse: reponseDemandeDeService.dateReponse != null ? reponseDemandeDeService.dateReponse.format(DATE_TIME_FORMAT) : null,
      demandeDeService: reponseDemandeDeService.demandeDeService
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const reponseDemandeDeService = this.createFromForm();
    if (reponseDemandeDeService.id !== undefined) {
      this.subscribeToSaveResponse(this.reponseDemandeDeServiceService.update(reponseDemandeDeService));
    } else {
      this.subscribeToSaveResponse(this.reponseDemandeDeServiceService.create(reponseDemandeDeService));
    }
  }

  private createFromForm(): IReponseDemandeDeService {
    return {
      ...new ReponseDemandeDeService(),
      id: this.editForm.get(['id']).value,
      dateReponse:
        this.editForm.get(['dateReponse']).value != null ? moment(this.editForm.get(['dateReponse']).value, DATE_TIME_FORMAT) : undefined,
      demandeDeService: this.editForm.get(['demandeDeService']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReponseDemandeDeService>>) {
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
}
