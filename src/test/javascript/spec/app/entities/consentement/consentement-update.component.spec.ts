import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ConsentementUpdateComponent } from 'app/entities/consentement/consentement-update.component';
import { ConsentementService } from 'app/entities/consentement/consentement.service';
import { Consentement } from 'app/shared/model/consentement.model';

describe('Component Tests', () => {
  describe('Consentement Management Update Component', () => {
    let comp: ConsentementUpdateComponent;
    let fixture: ComponentFixture<ConsentementUpdateComponent>;
    let service: ConsentementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ConsentementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ConsentementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsentementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsentementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Consentement(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Consentement();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
