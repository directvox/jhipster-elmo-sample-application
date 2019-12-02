import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ConsentementDetailComponent } from 'app/entities/consentement/consentement-detail.component';
import { Consentement } from 'app/shared/model/consentement.model';

describe('Component Tests', () => {
  describe('Consentement Management Detail Component', () => {
    let comp: ConsentementDetailComponent;
    let fixture: ComponentFixture<ConsentementDetailComponent>;
    const route = ({ data: of({ consentement: new Consentement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ConsentementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ConsentementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsentementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.consentement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
