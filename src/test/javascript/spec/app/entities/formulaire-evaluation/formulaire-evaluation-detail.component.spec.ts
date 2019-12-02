import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireEvaluationDetailComponent } from 'app/entities/formulaire-evaluation/formulaire-evaluation-detail.component';
import { FormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';

describe('Component Tests', () => {
  describe('FormulaireEvaluation Management Detail Component', () => {
    let comp: FormulaireEvaluationDetailComponent;
    let fixture: ComponentFixture<FormulaireEvaluationDetailComponent>;
    const route = ({ data: of({ formulaireEvaluation: new FormulaireEvaluation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireEvaluationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FormulaireEvaluationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormulaireEvaluationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formulaireEvaluation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
