import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { SpecCliniqueDetailComponent } from 'app/entities/spec-clinique/spec-clinique-detail.component';
import { SpecClinique } from 'app/shared/model/spec-clinique.model';

describe('Component Tests', () => {
  describe('SpecClinique Management Detail Component', () => {
    let comp: SpecCliniqueDetailComponent;
    let fixture: ComponentFixture<SpecCliniqueDetailComponent>;
    const route = ({ data: of({ specClinique: new SpecClinique(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [SpecCliniqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SpecCliniqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecCliniqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specClinique).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
