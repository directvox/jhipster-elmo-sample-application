import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { IntervenantComponent } from 'app/entities/intervenant/intervenant.component';
import { IntervenantService } from 'app/entities/intervenant/intervenant.service';
import { Intervenant } from 'app/shared/model/intervenant.model';

describe('Component Tests', () => {
  describe('Intervenant Management Component', () => {
    let comp: IntervenantComponent;
    let fixture: ComponentFixture<IntervenantComponent>;
    let service: IntervenantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [IntervenantComponent],
        providers: []
      })
        .overrideTemplate(IntervenantComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IntervenantComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IntervenantService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Intervenant(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.intervenants[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
