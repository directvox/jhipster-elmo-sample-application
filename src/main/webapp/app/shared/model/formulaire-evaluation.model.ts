import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';

export interface IFormulaireEvaluation {
  id?: number;
  typeFormulaire?: string;
  demandeDeServices?: IDemandeDeService[];
}

export class FormulaireEvaluation implements IFormulaireEvaluation {
  constructor(public id?: number, public typeFormulaire?: string, public demandeDeServices?: IDemandeDeService[]) {}
}
