import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

export interface IAttachementDemandeDeService {
  id?: number;
  nom?: string;
  fichierContentType?: string;
  fichier?: any;
  refExterne?: string;
  note?: any;
  ref?: IDemandeDeService;
}

export class AttachementDemandeDeService implements IAttachementDemandeDeService {
  constructor(
    public id?: number,
    public nom?: string,
    public fichierContentType?: string,
    public fichier?: any,
    public refExterne?: string,
    public note?: any,
    public ref?: IDemandeDeService
  ) {}
}
