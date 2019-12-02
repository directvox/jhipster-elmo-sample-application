import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ReponseDemandeDeServiceDetailComponent } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service-detail.component';
import { ReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';

describe('Component Tests', () => {
  describe('ReponseDemandeDeService Management Detail Component', () => {
    let comp: ReponseDemandeDeServiceDetailComponent;
    let fixture: ComponentFixture<ReponseDemandeDeServiceDetailComponent>;
    const route = ({ data: of({ reponseDemandeDeService: new ReponseDemandeDeService(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ReponseDemandeDeServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReponseDemandeDeServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReponseDemandeDeServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reponseDemandeDeService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
