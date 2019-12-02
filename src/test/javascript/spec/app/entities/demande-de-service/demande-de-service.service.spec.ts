import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DemandeDeServiceService } from 'app/entities/demande-de-service/demande-de-service.service';
import { IDemandeDeService, DemandeDeService } from 'app/shared/model/demande-de-service.model';
import { StatutDemande } from 'app/shared/model/enumerations/statut-demande.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { Priorite } from 'app/shared/model/enumerations/priorite.model';
import { Modalite } from 'app/shared/model/enumerations/modalite.model';
import { TypeActivite } from 'app/shared/model/enumerations/type-activite.model';

describe('Service Tests', () => {
  describe('DemandeDeService Service', () => {
    let injector: TestBed;
    let service: DemandeDeServiceService;
    let httpMock: HttpTestingController;
    let elemDefault: IDemandeDeService;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DemandeDeServiceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DemandeDeService(
        0,
        StatutDemande.ENCOURS,
        currentDate,
        Language.FRANCAIS,
        Priorite.URGENT,
        Modalite.DIFERE,
        TypeActivite.DISCUSSION
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateCreationDemande: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a DemandeDeService', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateCreationDemande: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateCreationDemande: currentDate
          },
          returnedFromService
        );
        service
          .create(new DemandeDeService(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a DemandeDeService', () => {
        const returnedFromService = Object.assign(
          {
            statut: 'BBBBBB',
            dateCreationDemande: currentDate.format(DATE_TIME_FORMAT),
            lanque: 'BBBBBB',
            priorite: 'BBBBBB',
            modalite: 'BBBBBB',
            typeActivite: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreationDemande: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of DemandeDeService', () => {
        const returnedFromService = Object.assign(
          {
            statut: 'BBBBBB',
            dateCreationDemande: currentDate.format(DATE_TIME_FORMAT),
            lanque: 'BBBBBB',
            priorite: 'BBBBBB',
            modalite: 'BBBBBB',
            typeActivite: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateCreationDemande: currentDate
          },
          returnedFromService
        );
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

      it('should delete a DemandeDeService', () => {
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
