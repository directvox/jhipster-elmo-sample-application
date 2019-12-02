import { IConsentement } from 'app/shared/model/consentement.model';

export interface IFormulaireConsentement {
  id?: number;
  idFormulaire?: string;
  nomFormulaire?: string;
  dateFormulaire?: string;
  actif?: boolean;
  formulaireConsentementTexte?: any;
  formulaireConsentementPDFContentType?: string;
  formulaireConsentementPDF?: any;
  formulaireConsentementURL?: string;
  consentement?: IConsentement;
}

export class FormulaireConsentement implements IFormulaireConsentement {
  constructor(
    public id?: number,
    public idFormulaire?: string,
    public nomFormulaire?: string,
    public dateFormulaire?: string,
    public actif?: boolean,
    public formulaireConsentementTexte?: any,
    public formulaireConsentementPDFContentType?: string,
    public formulaireConsentementPDF?: any,
    public formulaireConsentementURL?: string,
    public consentement?: IConsentement
  ) {
    this.actif = this.actif || false;
  }
}
