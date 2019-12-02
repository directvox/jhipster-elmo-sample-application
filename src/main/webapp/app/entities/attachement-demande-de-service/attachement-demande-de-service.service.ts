import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAttachementDemandeDeService } from 'app/shared/model/attachement-demande-de-service.model';

type EntityResponseType = HttpResponse<IAttachementDemandeDeService>;
type EntityArrayResponseType = HttpResponse<IAttachementDemandeDeService[]>;

@Injectable({ providedIn: 'root' })
export class AttachementDemandeDeServiceService {
  public resourceUrl = SERVER_API_URL + 'api/attachement-demande-de-services';

  constructor(protected http: HttpClient) {}

  create(attachementDemandeDeService: IAttachementDemandeDeService): Observable<EntityResponseType> {
    return this.http.post<IAttachementDemandeDeService>(this.resourceUrl, attachementDemandeDeService, { observe: 'response' });
  }

  update(attachementDemandeDeService: IAttachementDemandeDeService): Observable<EntityResponseType> {
    return this.http.put<IAttachementDemandeDeService>(this.resourceUrl, attachementDemandeDeService, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAttachementDemandeDeService>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAttachementDemandeDeService[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
