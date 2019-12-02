import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { SpecCliniqueUpdateComponent } from 'app/entities/spec-clinique/spec-clinique-update.component';
import { SpecCliniqueService } from 'app/entities/spec-clinique/spec-clinique.service';
import { SpecClinique } from 'app/shared/model/spec-clinique.model';

describe('Component Tests', () => {
  describe('SpecClinique Management Update Component', () => {
    let comp: SpecCliniqueUpdateComponent;
    let fixture: ComponentFixture<SpecCliniqueUpdateComponent>;
    let service: SpecCliniqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [SpecCliniqueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SpecCliniqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecCliniqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecCliniqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpecClinique(123);
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
        const entity = new SpecClinique();
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
