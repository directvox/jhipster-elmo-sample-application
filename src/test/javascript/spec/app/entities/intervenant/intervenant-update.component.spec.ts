import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { IntervenantUpdateComponent } from 'app/entities/intervenant/intervenant-update.component';
import { IntervenantService } from 'app/entities/intervenant/intervenant.service';
import { Intervenant } from 'app/shared/model/intervenant.model';

describe('Component Tests', () => {
  describe('Intervenant Management Update Component', () => {
    let comp: IntervenantUpdateComponent;
    let fixture: ComponentFixture<IntervenantUpdateComponent>;
    let service: IntervenantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [IntervenantUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(IntervenantUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IntervenantUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IntervenantService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Intervenant(123);
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
        const entity = new Intervenant();
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
