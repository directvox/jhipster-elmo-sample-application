import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IIntervenant, Intervenant } from 'app/shared/model/intervenant.model';
import { IntervenantService } from './intervenant.service';

@Component({
  selector: 'jhi-intervenant-update',
  templateUrl: './intervenant-update.component.html'
})
export class IntervenantUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    email: [],
    phoneNumber: []
  });

  constructor(protected intervenantService: IntervenantService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ intervenant }) => {
      this.updateForm(intervenant);
    });
  }

  updateForm(intervenant: IIntervenant) {
    this.editForm.patchValue({
      id: intervenant.id,
      firstName: intervenant.firstName,
      lastName: intervenant.lastName,
      email: intervenant.email,
      phoneNumber: intervenant.phoneNumber
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const intervenant = this.createFromForm();
    if (intervenant.id !== undefined) {
      this.subscribeToSaveResponse(this.intervenantService.update(intervenant));
    } else {
      this.subscribeToSaveResponse(this.intervenantService.create(intervenant));
    }
  }

  private createFromForm(): IIntervenant {
    return {
      ...new Intervenant(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      email: this.editForm.get(['email']).value,
      phoneNumber: this.editForm.get(['phoneNumber']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIntervenant>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
