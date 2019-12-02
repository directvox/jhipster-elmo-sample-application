import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireEvaluationUpdateComponent } from 'app/entities/formulaire-evaluation/formulaire-evaluation-update.component';
import { FormulaireEvaluationService } from 'app/entities/formulaire-evaluation/formulaire-evaluation.service';
import { FormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';

describe('Component Tests', () => {
  describe('FormulaireEvaluation Management Update Component', () => {
    let comp: FormulaireEvaluationUpdateComponent;
    let fixture: ComponentFixture<FormulaireEvaluationUpdateComponent>;
    let service: FormulaireEvaluationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireEvaluationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FormulaireEvaluationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormulaireEvaluationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulaireEvaluationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormulaireEvaluation(123);
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
        const entity = new FormulaireEvaluation();
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
