import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { DemandeDeServiceDetailComponent } from 'app/entities/demande-de-service/demande-de-service-detail.component';
import { DemandeDeService } from 'app/shared/model/demande-de-service.model';

describe('Component Tests', () => {
  describe('DemandeDeService Management Detail Component', () => {
    let comp: DemandeDeServiceDetailComponent;
    let fixture: ComponentFixture<DemandeDeServiceDetailComponent>;
    const route = ({ data: of({ demandeDeService: new DemandeDeService(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [DemandeDeServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DemandeDeServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DemandeDeServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.demandeDeService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
