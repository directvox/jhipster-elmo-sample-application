import { Component, OnInit, ElementRef } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IFormulaireConsentement, FormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';
import { FormulaireConsentementService } from './formulaire-consentement.service';
import { IConsentement } from 'app/shared/model/consentement.model';
import { ConsentementService } from 'app/entities/consentement/consentement.service';

@Component({
  selector: 'jhi-formulaire-consentement-update',
  templateUrl: './formulaire-consentement-update.component.html'
})
export class FormulaireConsentementUpdateComponent implements OnInit {
  isSaving: boolean;

  consentements: IConsentement[];

  editForm = this.fb.group({
    id: [],
    idFormulaire: [],
    nomFormulaire: [],
    dateFormulaire: [],
    actif: [],
    formulaireConsentementTexte: [],
    formulaireConsentementPDF: [],
    formulaireConsentementPDFContentType: [],
    formulaireConsentementURL: [],
    consentement: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected formulaireConsentementService: FormulaireConsentementService,
    protected consentementService: ConsentementService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ formulaireConsentement }) => {
      this.updateForm(formulaireConsentement);
    });
    this.consentementService.query({ filter: 'formulaireconsentement-is-null' }).subscribe(
      (res: HttpResponse<IConsentement[]>) => {
        if (!this.editForm.get('consentement').value || !this.editForm.get('consentement').value.id) {
          this.consentements = res.body;
        } else {
          this.consentementService
            .find(this.editForm.get('consentement').value.id)
            .subscribe(
              (subRes: HttpResponse<IConsentement>) => (this.consentements = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(formulaireConsentement: IFormulaireConsentement) {
    this.editForm.patchValue({
      id: formulaireConsentement.id,
      idFormulaire: formulaireConsentement.idFormulaire,
      nomFormulaire: formulaireConsentement.nomFormulaire,
      dateFormulaire: formulaireConsentement.dateFormulaire,
      actif: formulaireConsentement.actif,
      formulaireConsentementTexte: formulaireConsentement.formulaireConsentementTexte,
      formulaireConsentementPDF: formulaireConsentement.formulaireConsentementPDF,
      formulaireConsentementPDFContentType: formulaireConsentement.formulaireConsentementPDFContentType,
      formulaireConsentementURL: formulaireConsentement.formulaireConsentementURL,
      consentement: formulaireConsentement.consentement
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
    const formulaireConsentement = this.createFromForm();
    if (formulaireConsentement.id !== undefined) {
      this.subscribeToSaveResponse(this.formulaireConsentementService.update(formulaireConsentement));
    } else {
      this.subscribeToSaveResponse(this.formulaireConsentementService.create(formulaireConsentement));
    }
  }

  private createFromForm(): IFormulaireConsentement {
    return {
      ...new FormulaireConsentement(),
      id: this.editForm.get(['id']).value,
      idFormulaire: this.editForm.get(['idFormulaire']).value,
      nomFormulaire: this.editForm.get(['nomFormulaire']).value,
      dateFormulaire: this.editForm.get(['dateFormulaire']).value,
      actif: this.editForm.get(['actif']).value,
      formulaireConsentementTexte: this.editForm.get(['formulaireConsentementTexte']).value,
      formulaireConsentementPDFContentType: this.editForm.get(['formulaireConsentementPDFContentType']).value,
      formulaireConsentementPDF: this.editForm.get(['formulaireConsentementPDF']).value,
      formulaireConsentementURL: this.editForm.get(['formulaireConsentementURL']).value,
      consentement: this.editForm.get(['consentement']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormulaireConsentement>>) {
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

  trackConsentementById(index: number, item: IConsentement) {
    return item.id;
  }
}
