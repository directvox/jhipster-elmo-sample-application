import { IIntervenant } from 'app/shared/model/intervenant.model';
import { ISpecClinique } from 'app/shared/model/spec-clinique.model';

export interface IDestinataire {
  id?: number;
  nomDestinataire?: string;
  ensembreDestinataire?: IIntervenant;
  specCliniques?: ISpecClinique[];
}

export class Destinataire implements IDestinataire {
  constructor(
    public id?: number,
    public nomDestinataire?: string,
    public ensembreDestinataire?: IIntervenant,
    public specCliniques?: ISpecClinique[]
  ) {}
}
