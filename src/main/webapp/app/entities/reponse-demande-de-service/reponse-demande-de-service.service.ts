import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReponseDemandeDeService } from 'app/shared/model/reponse-demande-de-service.model';

type EntityResponseType = HttpResponse<IReponseDemandeDeService>;
type EntityArrayResponseType = HttpResponse<IReponseDemandeDeService[]>;

@Injectable({ providedIn: 'root' })
export class ReponseDemandeDeServiceService {
  public resourceUrl = SERVER_API_URL + 'api/reponse-demande-de-services';

  constructor(protected http: HttpClient) {}

  create(reponseDemandeDeService: IReponseDemandeDeService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reponseDemandeDeService);
    return this.http
      .post<IReponseDemandeDeService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reponseDemandeDeService: IReponseDemandeDeService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reponseDemandeDeService);
    return this.http
      .put<IReponseDemandeDeService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReponseDemandeDeService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReponseDemandeDeService[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reponseDemandeDeService: IReponseDemandeDeService): IReponseDemandeDeService {
    const copy: IReponseDemandeDeService = Object.assign({}, reponseDemandeDeService, {
      dateReponse:
        reponseDemandeDeService.dateReponse != null && reponseDemandeDeService.dateReponse.isValid()
          ? reponseDemandeDeService.dateReponse.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReponse = res.body.dateReponse != null ? moment(res.body.dateReponse) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((reponseDemandeDeService: IReponseDemandeDeService) => {
        reponseDemandeDeService.dateReponse =
          reponseDemandeDeService.dateReponse != null ? moment(reponseDemandeDeService.dateReponse) : null;
      });
    }
    return res;
  }
}
