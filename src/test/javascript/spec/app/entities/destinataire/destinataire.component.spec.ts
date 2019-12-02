import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { DestinataireComponent } from 'app/entities/destinataire/destinataire.component';
import { DestinataireService } from 'app/entities/destinataire/destinataire.service';
import { Destinataire } from 'app/shared/model/destinataire.model';

describe('Component Tests', () => {
  describe('Destinataire Management Component', () => {
    let comp: DestinataireComponent;
    let fixture: ComponentFixture<DestinataireComponent>;
    let service: DestinataireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [DestinataireComponent],
        providers: []
      })
        .overrideTemplate(DestinataireComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DestinataireComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DestinataireService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Destinataire(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.destinataires[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
