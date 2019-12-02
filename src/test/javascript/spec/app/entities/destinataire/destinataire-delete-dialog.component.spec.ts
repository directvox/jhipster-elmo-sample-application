import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { DestinataireDeleteDialogComponent } from 'app/entities/destinataire/destinataire-delete-dialog.component';
import { DestinataireService } from 'app/entities/destinataire/destinataire.service';

describe('Component Tests', () => {
  describe('Destinataire Management Delete Component', () => {
    let comp: DestinataireDeleteDialogComponent;
    let fixture: ComponentFixture<DestinataireDeleteDialogComponent>;
    let service: DestinataireService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [DestinataireDeleteDialogComponent]
      })
        .overrideTemplate(DestinataireDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DestinataireDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DestinataireService);
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
