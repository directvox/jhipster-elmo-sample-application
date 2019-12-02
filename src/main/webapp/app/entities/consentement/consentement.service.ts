import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConsentement } from 'app/shared/model/consentement.model';

type EntityResponseType = HttpResponse<IConsentement>;
type EntityArrayResponseType = HttpResponse<IConsentement[]>;

@Injectable({ providedIn: 'root' })
export class ConsentementService {
  public resourceUrl = SERVER_API_URL + 'api/consentements';

  constructor(protected http: HttpClient) {}

  create(consentement: IConsentement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(consentement);
    return this.http
      .post<IConsentement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(consentement: IConsentement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(consentement);
    return this.http
      .put<IConsentement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IConsentement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConsentement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(consentement: IConsentement): IConsentement {
    const copy: IConsentement = Object.assign({}, consentement, {
      dateConsentement:
        consentement.dateConsentement != null && consentement.dateConsentement.isValid() ? consentement.dateConsentement.toJSON() : null,
      dateSupprimerConsentement:
        consentement.dateSupprimerConsentement != null && consentement.dateSupprimerConsentement.isValid()
          ? consentement.dateSupprimerConsentement.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateConsentement = res.body.dateConsentement != null ? moment(res.body.dateConsentement) : null;
      res.body.dateSupprimerConsentement = res.body.dateSupprimerConsentement != null ? moment(res.body.dateSupprimerConsentement) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((consentement: IConsentement) => {
        consentement.dateConsentement = consentement.dateConsentement != null ? moment(consentement.dateConsentement) : null;
        consentement.dateSupprimerConsentement =
          consentement.dateSupprimerConsentement != null ? moment(consentement.dateSupprimerConsentement) : null;
      });
    }
    return res;
  }
}
