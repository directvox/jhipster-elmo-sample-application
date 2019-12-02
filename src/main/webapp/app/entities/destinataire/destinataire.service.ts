import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDestinataire } from 'app/shared/model/destinataire.model';

type EntityResponseType = HttpResponse<IDestinataire>;
type EntityArrayResponseType = HttpResponse<IDestinataire[]>;

@Injectable({ providedIn: 'root' })
export class DestinataireService {
  public resourceUrl = SERVER_API_URL + 'api/destinataires';

  constructor(protected http: HttpClient) {}

  create(destinataire: IDestinataire): Observable<EntityResponseType> {
    return this.http.post<IDestinataire>(this.resourceUrl, destinataire, { observe: 'response' });
  }

  update(destinataire: IDestinataire): Observable<EntityResponseType> {
    return this.http.put<IDestinataire>(this.resourceUrl, destinataire, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDestinataire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDestinataire[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
