import { Component, OnInit, ElementRef } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IAttachementDemandeDeService, AttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';
import { AttachementDemandeDeServiceService } from './attachement-demande-de-service.service';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';
import { DemandeDeServiceService } from 'app/entities/demande-de-service/demande-de-service.service';

@Component({
  selector: 'jhi-attachement-demande-de-service-update',
  templateUrl: './attachement-demande-de-service-update.component.html'
})
export class AttachementDemandeDeServiceUpdateComponent implements OnInit {
  isSaving: boolean;

  refs: IDemandeDeService[];

  editForm = this.fb.group({
    id: [],
    nom: [],
    fichier: [],
    fichierContentType: [],
    refExterne: [],
    note: [],
    ref: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected attachementDemandeDeServiceService: AttachementDemandeDeServiceService,
    protected demandeDeServiceService: DemandeDeServiceService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ attachementDemandeDeService }) => {
      this.updateForm(attachementDemandeDeService);
    });
    this.demandeDeServiceService.query({ filter: 'attachementdemandedeservice-is-null' }).subscribe(
      (res: HttpResponse<IDemandeDeService[]>) => {
        if (!this.editForm.get('ref').value || !this.editForm.get('ref').value.id) {
          this.refs = res.body;
        } else {
          this.demandeDeServiceService
            .find(this.editForm.get('ref').value.id)
            .subscribe(
              (subRes: HttpResponse<IDemandeDeService>) => (this.refs = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(attachementDemandeDeService: IAttachementDemandeDeService) {
    this.editForm.patchValue({
      id: attachementDemandeDeService.id,
      nom: attachementDemandeDeService.nom,
      fichier: attachementDemandeDeService.fichier,
      fichierContentType: attachementDemandeDeService.fichierContentType,
      refExterne: attachementDemandeDeService.refExterne,
      note: attachementDemandeDeService.note,
      ref: attachementDemandeDeService.ref
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
    const attachementDemandeDeService = this.createFromForm();
    if (attachementDemandeDeService.id !== undefined) {
      this.subscribeToSaveResponse(this.attachementDemandeDeServiceService.update(attachementDemandeDeService));
    } else {
      this.subscribeToSaveResponse(this.attachementDemandeDeServiceService.create(attachementDemandeDeService));
    }
  }

  private createFromForm(): IAttachementDemandeDeService {
    return {
      ...new AttachementDemandeDeService(),
      id: this.editForm.get(['id']).value,
      nom: this.editForm.get(['nom']).value,
      fichierContentType: this.editForm.get(['fichierContentType']).value,
      fichier: this.editForm.get(['fichier']).value,
      refExterne: this.editForm.get(['refExterne']).value,
      note: this.editForm.get(['note']).value,
      ref: this.editForm.get(['ref']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAttachementDemandeDeService>>) {
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
