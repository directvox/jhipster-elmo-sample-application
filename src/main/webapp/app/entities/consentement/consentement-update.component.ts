import { Component, OnInit, ElementRef } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IConsentement, Consentement } from 'app/shared/model/consentement.model';
import { ConsentementService } from './consentement.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-consentement-update',
  templateUrl: './consentement-update.component.html'
})
export class ConsentementUpdateComponent implements OnInit {
  isSaving: boolean;

  patients: IPatient[];

  editForm = this.fb.group({
    id: [],
    typeConsentement: [null, [Validators.required]],
    dateConsentement: [null, [Validators.required]],
    consentementRecherche: [null, [Validators.required]],
    consentementPDF: [null, [Validators.required]],
    consentementPDFContentType: [],
    dateSupprimerConsentement: [],
    patient: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected consentementService: ConsentementService,
    protected patientService: PatientService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ consentement }) => {
      this.updateForm(consentement);
    });
    this.patientService.query({ filter: 'consentement-is-null' }).subscribe(
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
  }

  updateForm(consentement: IConsentement) {
    this.editForm.patchValue({
      id: consentement.id,
      typeConsentement: consentement.typeConsentement,
      dateConsentement: consentement.dateConsentement != null ? consentement.dateConsentement.format(DATE_TIME_FORMAT) : null,
      consentementRecherche: consentement.consentementRecherche,
      consentementPDF: consentement.consentementPDF,
      consentementPDFContentType: consentement.consentementPDFContentType,
      dateSupprimerConsentement:
        consentement.dateSupprimerConsentement != null ? consentement.dateSupprimerConsentement.format(DATE_TIME_FORMAT) : null,
      patient: consentement.patient
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file: File = event.target.files[0];
        if (isImage && !file.type.startsWith('image/')) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      // eslint-disable-next-line no-console
      () => console.log('blob added'), // success
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const consentement = this.createFromForm();
    if (consentement.id !== undefined) {
      this.subscribeToSaveResponse(this.consentementService.update(consentement));
    } else {
      this.subscribeToSaveResponse(this.consentementService.create(consentement));
    }
  }

  private createFromForm(): IConsentement {
    return {
      ...new Consentement(),
      id: this.editForm.get(['id']).value,
      typeConsentement: this.editForm.get(['typeConsentement']).value,
      dateConsentement:
        this.editForm.get(['dateConsentement']).value != null
          ? moment(this.editForm.get(['dateConsentement']).value, DATE_TIME_FORMAT)
          : undefined,
      consentementRecherche: this.editForm.get(['consentementRecherche']).value,
      consentementPDFContentType: this.editForm.get(['consentementPDFContentType']).value,
      consentementPDF: this.editForm.get(['consentementPDF']).value,
      dateSupprimerConsentement:
        this.editForm.get(['dateSupprimerConsentement']).value != null
          ? moment(this.editForm.get(['dateSupprimerConsentement']).value, DATE_TIME_FORMAT)
          : undefined,
      patient: this.editForm.get(['patient']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConsentement>>) {
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
}
