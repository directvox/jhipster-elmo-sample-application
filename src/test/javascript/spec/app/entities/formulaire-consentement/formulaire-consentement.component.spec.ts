import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireConsentementComponent } from 'app/entities/formulaire-consentement/formulaire-consentement.component';
import { FormulaireConsentementService } from 'app/entities/formulaire-consentement/formulaire-consentement.service';
import { FormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

describe('Component Tests', () => {
  describe('FormulaireConsentement Management Component', () => {
    let comp: FormulaireConsentementComponent;
    let fixture: ComponentFixture<FormulaireConsentementComponent>;
    let service: FormulaireConsentementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireConsentementComponent],
        providers: []
      })
        .overrideTemplate(FormulaireConsentementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormulaireConsentementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulaireConsentementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormulaireConsentement(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formulaireConsentements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
