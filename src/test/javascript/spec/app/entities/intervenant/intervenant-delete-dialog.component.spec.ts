import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { IntervenantDeleteDialogComponent } from 'app/entities/intervenant/intervenant-delete-dialog.component';
import { IntervenantService } from 'app/entities/intervenant/intervenant.service';

describe('Component Tests', () => {
  describe('Intervenant Management Delete Component', () => {
    let comp: IntervenantDeleteDialogComponent;
    let fixture: ComponentFixture<IntervenantDeleteDialogComponent>;
    let service: IntervenantService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [IntervenantDeleteDialogComponent]
      })
        .overrideTemplate(IntervenantDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IntervenantDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IntervenantService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
