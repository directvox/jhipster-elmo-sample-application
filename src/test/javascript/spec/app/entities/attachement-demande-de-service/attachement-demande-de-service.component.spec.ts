import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { AttachementDemandeDeServiceComponent } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service.component';
import { AttachementDemandeDeServiceService } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service.service';
import { AttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

describe('Component Tests', () => {
  describe('AttachementDemandeDeService Management Component', () => {
    let comp: AttachementDemandeDeServiceComponent;
    let fixture: ComponentFixture<AttachementDemandeDeServiceComponent>;
    let service: AttachementDemandeDeServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [AttachementDemandeDeServiceComponent],
        providers: []
      })
        .overrideTemplate(AttachementDemandeDeServiceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AttachementDemandeDeServiceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AttachementDemandeDeServiceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AttachementDemandeDeService(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.attachementDemandeDeServices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
