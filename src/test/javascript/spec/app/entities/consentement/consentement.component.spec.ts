import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ConsentementComponent } from 'app/entities/consentement/consentement.component';
import { ConsentementService } from 'app/entities/consentement/consentement.service';
import { Consentement } from 'app/shared/model/consentement.model';

describe('Component Tests', () => {
  describe('Consentement Management Component', () => {
    let comp: ConsentementComponent;
    let fixture: ComponentFixture<ConsentementComponent>;
    let service: ConsentementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ConsentementComponent],
        providers: []
      })
        .overrideTemplate(ConsentementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsentementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsentementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Consentement(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.consentements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
