import { Moment } from 'moment';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

export interface IReponseDemandeDeService {
  id?: number;
  dateReponse?: Moment;
  demandeDeService?: IDemandeDeService;
}

export class ReponseDemandeDeService implements IReponseDemandeDeService {
  constructor(public id?: number, public dateReponse?: Moment, public demandeDeService?: IDemandeDeService) {}
}
