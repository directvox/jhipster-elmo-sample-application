import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { SpecCliniqueDeleteDialogComponent } from 'app/entities/spec-clinique/spec-clinique-delete-dialog.component';
import { SpecCliniqueService } from 'app/entities/spec-clinique/spec-clinique.service';

describe('Component Tests', () => {
  describe('SpecClinique Management Delete Component', () => {
    let comp: SpecCliniqueDeleteDialogComponent;
    let fixture: ComponentFixture<SpecCliniqueDeleteDialogComponent>;
    let service: SpecCliniqueService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [SpecCliniqueDeleteDialogComponent]
      })
        .overrideTemplate(SpecCliniqueDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecCliniqueDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecCliniqueService);
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
