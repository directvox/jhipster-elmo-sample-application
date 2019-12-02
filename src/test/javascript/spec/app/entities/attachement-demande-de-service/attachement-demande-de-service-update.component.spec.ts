import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { AttachementDemandeDeServiceUpdateComponent } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service-update.component';
import { AttachementDemandeDeServiceService } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service.service';
import { AttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

describe('Component Tests', () => {
  describe('AttachementDemandeDeService Management Update Component', () => {
    let comp: AttachementDemandeDeServiceUpdateComponent;
    let fixture: ComponentFixture<AttachementDemandeDeServiceUpdateComponent>;
    let service: AttachementDemandeDeServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [AttachementDemandeDeServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AttachementDemandeDeServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AttachementDemandeDeServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AttachementDemandeDeServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AttachementDemandeDeService(123);
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
        const entity = new AttachementDemandeDeService();
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
