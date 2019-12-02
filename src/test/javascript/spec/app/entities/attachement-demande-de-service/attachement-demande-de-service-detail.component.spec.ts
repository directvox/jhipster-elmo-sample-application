import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { AttachementDemandeDeServiceDetailComponent } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service-detail.component';
import { AttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

describe('Component Tests', () => {
  describe('AttachementDemandeDeService Management Detail Component', () => {
    let comp: AttachementDemandeDeServiceDetailComponent;
    let fixture: ComponentFixture<AttachementDemandeDeServiceDetailComponent>;
    const route = ({ data: of({ attachementDemandeDeService: new AttachementDemandeDeService(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [AttachementDemandeDeServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AttachementDemandeDeServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AttachementDemandeDeServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.attachementDemandeDeService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
