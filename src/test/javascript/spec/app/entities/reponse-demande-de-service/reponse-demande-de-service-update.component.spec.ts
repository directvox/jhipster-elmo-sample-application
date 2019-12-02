import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ReponseDemandeDeServiceUpdateComponent } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service-update.component';
import { ReponseDemandeDeServiceService } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service.service';
import { ReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';

describe('Component Tests', () => {
  describe('ReponseDemandeDeService Management Update Component', () => {
    let comp: ReponseDemandeDeServiceUpdateComponent;
    let fixture: ComponentFixture<ReponseDemandeDeServiceUpdateComponent>;
    let service: ReponseDemandeDeServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ReponseDemandeDeServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ReponseDemandeDeServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReponseDemandeDeServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReponseDemandeDeServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReponseDemandeDeService(123);
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
        const entity = new ReponseDemandeDeService();
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
