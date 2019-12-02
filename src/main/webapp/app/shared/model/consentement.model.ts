import { Moment } from 'moment';
import { IPatient } from 'app/shared/model/patient.model';
import { TypeConsentement } from 'app/shared/model/enumerations/type-consentement.model';

export interface IConsentement {
  id?: number;
  typeConsentement?: TypeConsentement;
  dateConsentement?: Moment;
  consentementRecherche?: boolean;
  consentementPDFContentType?: string;
  consentementPDF?: any;
  dateSupprimerConsentement?: Moment;
  patient?: IPatient;
}

export class Consentement implements IConsentement {
  constructor(
    public id?: number,
    public typeConsentement?: TypeConsentement,
    public dateConsentement?: Moment,
    public consentementRecherche?: boolean,
    public consentementPDFContentType?: string,
    public consentementPDF?: any,
    public dateSupprimerConsentement?: Moment,
    public patient?: IPatient
  ) {
    this.consentementRecherche = this.consentementRecherche || false;
  }
}
