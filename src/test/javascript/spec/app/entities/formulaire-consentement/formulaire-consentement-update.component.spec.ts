import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireConsentementUpdateComponent } from 'app/entities/formulaire-consentement/formulaire-consentement-update.component';
import { FormulaireConsentementService } from 'app/entities/formulaire-consentement/formulaire-consentement.service';
import { FormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

describe('Component Tests', () => {
  describe('FormulaireConsentement Management Update Component', () => {
    let comp: FormulaireConsentementUpdateComponent;
    let fixture: ComponentFixture<FormulaireConsentementUpdateComponent>;
    let service: FormulaireConsentementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireConsentementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FormulaireConsentementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormulaireConsentementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulaireConsentementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormulaireConsentement(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormulaireConsentement();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
