import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { DestinataireUpdateComponent } from 'app/entities/destinataire/destinataire-update.component';
import { DestinataireService } from 'app/entities/destinataire/destinataire.service';
import { Destinataire } from 'app/shared/model/destinataire.model';

describe('Component Tests', () => {
  describe('Destinataire Management Update Component', () => {
    let comp: DestinataireUpdateComponent;
    let fixture: ComponentFixture<DestinataireUpdateComponent>;
    let service: DestinataireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [DestinataireUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DestinataireUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DestinataireUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DestinataireService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Destinataire(123);
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
        const entity = new Destinataire();
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
