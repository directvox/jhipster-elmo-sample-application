import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireEvaluationComponent } from 'app/entities/formulaire-evaluation/formulaire-evaluation.component';
import { FormulaireEvaluationService } from 'app/entities/formulaire-evaluation/formulaire-evaluation.service';
import { FormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';

describe('Component Tests', () => {
  describe('FormulaireEvaluation Management Component', () => {
    let comp: FormulaireEvaluationComponent;
    let fixture: ComponentFixture<FormulaireEvaluationComponent>;
    let service: FormulaireEvaluationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireEvaluationComponent],
        providers: []
      })
        .overrideTemplate(FormulaireEvaluationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormulaireEvaluationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulaireEvaluationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormulaireEvaluation(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formulaireEvaluations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
