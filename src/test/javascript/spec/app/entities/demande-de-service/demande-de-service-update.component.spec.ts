import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { DemandeDeServiceUpdateComponent } from 'app/entities/demande-de-service/demande-de-service-update.component';
import { DemandeDeServiceService } from 'app/entities/demande-de-service/demande-de-service.service';
import { DemandeDeService } from 'app/shared/model/demande-de-service.model';

describe('Component Tests', () => {
  describe('DemandeDeService Management Update Component', () => {
    let comp: DemandeDeServiceUpdateComponent;
    let fixture: ComponentFixture<DemandeDeServiceUpdateComponent>;
    let service: DemandeDeServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [DemandeDeServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DemandeDeServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemandeDeServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemandeDeServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DemandeDeService(123);
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
        const entity = new DemandeDeService();
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
