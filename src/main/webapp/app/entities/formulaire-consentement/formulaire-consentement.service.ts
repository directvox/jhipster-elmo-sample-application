import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormulaireConsentement } from 'app/shared/model/formulaire-consentement.model';

type EntityResponseType = HttpResponse<IFormulaireConsentement>;
type EntityArrayResponseType = HttpResponse<IFormulaireConsentement[]>;

@Injectable({ providedIn: 'root' })
export class FormulaireConsentementService {
  public resourceUrl = SERVER_API_URL + 'api/formulaire-consentements';

  constructor(protected http: HttpClient) {}

  create(formulaireConsentement: IFormulaireConsentement): Observable<EntityResponseType> {
    return this.http.post<IFormulaireConsentement>(this.resourceUrl, formulaireConsentement, { observe: 'response' });
  }

  update(formulaireConsentement: IFormulaireConsentement): Observable<EntityResponseType> {
    return this.http.put<IFormulaireConsentement>(this.resourceUrl, formulaireConsentement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFormulaireConsentement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormulaireConsentement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
