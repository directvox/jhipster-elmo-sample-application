import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISpecClinique } from 'app/shared/model/spec-clinique.model';

type EntityResponseType = HttpResponse<ISpecClinique>;
type EntityArrayResponseType = HttpResponse<ISpecClinique[]>;

@Injectable({ providedIn: 'root' })
export class SpecCliniqueService {
  public resourceUrl = SERVER_API_URL + 'api/spec-cliniques';

  constructor(protected http: HttpClient) {}

  create(specClinique: ISpecClinique): Observable<EntityResponseType> {
    return this.http.post<ISpecClinique>(this.resourceUrl, specClinique, { observe: 'response' });
  }

  update(specClinique: ISpecClinique): Observable<EntityResponseType> {
    return this.http.put<ISpecClinique>(this.resourceUrl, specClinique, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpecClinique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpecClinique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
