import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ReponseDemandeDeServiceComponent } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service.component';
import { ReponseDemandeDeServiceService } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service.service';
import { ReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';

describe('Component Tests', () => {
  describe('ReponseDemandeDeService Management Component', () => {
    let comp: ReponseDemandeDeServiceComponent;
    let fixture: ComponentFixture<ReponseDemandeDeServiceComponent>;
    let service: ReponseDemandeDeServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ReponseDemandeDeServiceComponent],
        providers: []
      })
        .overrideTemplate(ReponseDemandeDeServiceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReponseDemandeDeServiceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReponseDemandeDeServiceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReponseDemandeDeService(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reponseDemandeDeServices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
