import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireConsentementDetailComponent } from 'app/entities/formulaire-consentement/formulaire-consentement-detail.component';
import { FormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

describe('Component Tests', () => {
  describe('FormulaireConsentement Management Detail Component', () => {
    let comp: FormulaireConsentementDetailComponent;
    let fixture: ComponentFixture<FormulaireConsentementDetailComponent>;
    const route = ({ data: of({ formulaireConsentement: new FormulaireConsentement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireConsentementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FormulaireConsentementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormulaireConsentementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formulaireConsentement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
