import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { AttachementDemandeDeServiceService } from 'app/entities/attachement-demande-de-service/attachement-demande-de-service.service';
import { IAttachementDemandeDeService, AttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

describe('Service Tests', () => {
  describe('AttachementDemandeDeService Service', () => {
    let injector: TestBed;
    let service: AttachementDemandeDeServiceService;
    let httpMock: HttpTestingController;
    let elemDefault: IAttachementDemandeDeService;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AttachementDemandeDeServiceService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AttachementDemandeDeService(0, 'AAAAAAA', 'image/png', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a AttachementDemandeDeService', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new AttachementDemandeDeService(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AttachementDemandeDeService', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            fichier: 'BBBBBB',
            refExterne: 'BBBBBB',
            note: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of AttachementDemandeDeService', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            fichier: 'BBBBBB',
            refExterne: 'BBBBBB',
            note: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AttachementDemandeDeService', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
