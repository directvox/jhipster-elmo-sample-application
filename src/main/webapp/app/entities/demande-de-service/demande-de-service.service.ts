import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

type EntityResponseType = HttpResponse<IDemandeDeService>;
type EntityArrayResponseType = HttpResponse<IDemandeDeService[]>;

@Injectable({ providedIn: 'root' })
export class DemandeDeServiceService {
  public resourceUrl = SERVER_API_URL + 'api/demande-de-services';

  constructor(protected http: HttpClient) {}

  create(demandeDeService: IDemandeDeService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demandeDeService);
    return this.http
      .post<IDemandeDeService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(demandeDeService: IDemandeDeService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demandeDeService);
    return this.http
      .put<IDemandeDeService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDemandeDeService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDemandeDeService[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(demandeDeService: IDemandeDeService): IDemandeDeService {
    const copy: IDemandeDeService = Object.assign({}, demandeDeService, {
      dateCreationDemande:
        demandeDeService.dateCreationDemande != null && demandeDeService.dateCreationDemande.isValid()
          ? demandeDeService.dateCreationDemande.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreationDemande = res.body.dateCreationDemande != null ? moment(res.body.dateCreationDemande) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((demandeDeService: IDemandeDeService) => {
        demandeDeService.dateCreationDemande =
          demandeDeService.dateCreationDemande != null ? moment(demandeDeService.dateCreationDemande) : null;
      });
    }
    return res;
  }
}
