import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ConsentementDeleteDialogComponent } from 'app/entities/consentement/consentement-delete-dialog.component';
import { ConsentementService } from 'app/entities/consentement/consentement.service';

describe('Component Tests', () => {
  describe('Consentement Management Delete Component', () => {
    let comp: ConsentementDeleteDialogComponent;
    let fixture: ComponentFixture<ConsentementDeleteDialogComponent>;
    let service: ConsentementService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ConsentementDeleteDialogComponent]
      })
        .overrideTemplate(ConsentementDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsentementDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsentementService);
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
