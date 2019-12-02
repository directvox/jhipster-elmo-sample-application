import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IIntervenant } from 'app/shared/model/intervenant.model';

type EntityResponseType = HttpResponse<IIntervenant>;
type EntityArrayResponseType = HttpResponse<IIntervenant[]>;

@Injectable({ providedIn: 'root' })
export class IntervenantService {
  public resourceUrl = SERVER_API_URL + 'api/intervenants';

  constructor(protected http: HttpClient) {}

  create(intervenant: IIntervenant): Observable<EntityResponseType> {
    return this.http.post<IIntervenant>(this.resourceUrl, intervenant, { observe: 'response' });
  }

  update(intervenant: IIntervenant): Observable<EntityResponseType> {
    return this.http.put<IIntervenant>(this.resourceUrl, intervenant, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIntervenant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIntervenant[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
