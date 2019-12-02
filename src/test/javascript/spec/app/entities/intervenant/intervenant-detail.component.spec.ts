import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { IntervenantDetailComponent } from 'app/entities/intervenant/intervenant-detail.component';
import { Intervenant } from 'app/shared/model/intervenant.model';

describe('Component Tests', () => {
  describe('Intervenant Management Detail Component', () => {
    let comp: IntervenantDetailComponent;
    let fixture: ComponentFixture<IntervenantDetailComponent>;
    const route = ({ data: of({ intervenant: new Intervenant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [IntervenantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(IntervenantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IntervenantDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.intervenant).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
