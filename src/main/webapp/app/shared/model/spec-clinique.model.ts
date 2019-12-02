import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { IDestinataire } from 'app/shared/model/destinataire.model';

export interface ISpecClinique {
  id?: number;
  nomSpecialiteClinique?: string;
  specialite?: IFormulaireEvaluation;
  destinataires?: IDestinataire[];
}

export class SpecClinique implements ISpecClinique {
  constructor(
    public id?: number,
    public nomSpecialiteClinique?: string,
    public specialite?: IFormulaireEvaluation,
    public destinataires?: IDestinataire[]
  ) {}
}
