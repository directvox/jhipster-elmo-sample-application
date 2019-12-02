import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterElmoSampleApplicationTestModule } from '../../../test.module';
import { ReponseDemandeDeServiceDeleteDialogComponent } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service-delete-dialog.component';
import { ReponseDemandeDeServiceService } from 'app/entities/reponse-demande-de-service/reponse-demande-de-service.service';

describe('Component Tests', () => {
  describe('ReponseDemandeDeService Management Delete Component', () => {
    let comp: ReponseDemandeDeServiceDeleteDialogComponent;
    let fixture: ComponentFixture<ReponseDemandeDeServiceDeleteDialogComponent>;
    let service: ReponseDemandeDeServiceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterElmoSampleApplicationTestModule],
        declarations: [ReponseDemandeDeServiceDeleteDialogComponent]
      })
        .overrideTemplate(ReponseDemandeDeServiceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReponseDemandeDeServiceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReponseDemandeDeServiceService);
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
