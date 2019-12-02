import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { FormulaireConsentementService } from 'app/entities/formulaire-consentement/formulaire-consentement.service';
import { IFormulaireConsentement, FormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

describe('Service Tests', () => {
  describe('FormulaireConsentement Service', () => {
    let injector: TestBed;
    let service: FormulaireConsentementService;
    let httpMock: HttpTestingController;
    let elemDefault: IFormulaireConsentement;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(FormulaireConsentementService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new FormulaireConsentement(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 'AAAAAAA', 'image/png', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a FormulaireConsentement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new FormulaireConsentement(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a FormulaireConsentement', () => {
        const returnedFromService = Object.assign(
          {
            idFormulaire: 'BBBBBB',
            nomFormulaire: 'BBBBBB',
            dateFormulaire: 'BBBBBB',
            actif: true,
            formulaireConsentementTexte: 'BBBBBB',
            formulaireConsentementPDF: 'BBBBBB',
            formulaireConsentementURL: 'BBBBBB'
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

      it('should return a list of FormulaireConsentement', () => {
        const returnedFromService = Object.assign(
          {
            idFormulaire: 'BBBBBB',
            nomFormulaire: 'BBBBBB',
            dateFormulaire: 'BBBBBB',
            actif: true,
            formulaireConsentementTexte: 'BBBBBB',
            formulaireConsentementPDF: 'BBBBBB',
            formulaireConsentementURL: 'BBBBBB'
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

      it('should delete a FormulaireConsentement', () => {
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
