import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { FormulaireEvaluationDeleteDialogComponent } from 'app/entities/formulaire-evaluation/formulaire-evaluation-delete-dialog.component';
import { FormulaireEvaluationService } from 'app/entities/formulaire-evaluation/formulaire-evaluation.service';

describe('Component Tests', () => {
  describe('FormulaireEvaluation Management Delete Component', () => {
    let comp: FormulaireEvaluationDeleteDialogComponent;
    let fixture: ComponentFixture<FormulaireEvaluationDeleteDialogComponent>;
    let service: FormulaireEvaluationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [FormulaireEvaluationDeleteDialogComponent]
      })
        .overrideTemplate(FormulaireEvaluationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormulaireEvaluationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulaireEvaluationService);
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
