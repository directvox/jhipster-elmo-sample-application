import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireConsentementDeleteDialogComponent } from 'app/entities/formulaire-consentement/formulaire-consentement-delete-dialog.component';
import { FormulaireConsentementService } from 'app/entities/formulaire-consentement/formulaire-consentement.service';

describe('Component Tests', () => {
  describe('FormulaireConsentement Management Delete Component', () => {
    let comp: FormulaireConsentementDeleteDialogComponent;
    let fixture: ComponentFixture<FormulaireConsentementDeleteDialogComponent>;
    let service: FormulaireConsentementService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireConsentementDeleteDialogComponent]
      })
        .overrideTemplate(FormulaireConsentementDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormulaireConsentementDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulaireConsentementService);
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
