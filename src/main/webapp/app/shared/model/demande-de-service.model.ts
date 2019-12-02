import { Moment } from 'moment';
import { IPatient } from 'app/shared/model/patient.model';
import { IDestinataire } from 'app/shared/model/destinataire.model';
import { ISpecClinique } from 'app/shared/model/spec-clinique.model';
import { IDemandeDeService } from 'app/shared/model/demande-de-service.model';
import { IFormulaireEvaluation } from 'app/shared/model/formulaire-evaluation.model';
import { StatutDemande } from 'app/shared/model/enumerations/statut-demande.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { Priorite } from 'app/shared/model/enumerations/priorite.model';
import { Modalite } from 'app/shared/model/enumerations/modalite.model';
import { TypeActivite } from 'app/shared/model/enumerations/type-activite.model';

export interface IDemandeDeService {
  id?: number;
  statut?: StatutDemande;
  dateCreationDemande?: Moment;
  lanque?: Language;
  priorite?: Priorite;
  modalite?: Modalite;
  typeActivite?: TypeActivite;
  patient?: IPatient;
  destinataire?: IDestinataire;
  specialiteClinique?: ISpecClinique;
  ensembre?: IDemandeDeService;
  formulaireEvaluations?: IFormulaireEvaluation[];
}

export class DemandeDeService implements IDemandeDeService {
  constructor(
    public id?: number,
    public statut?: StatutDemande,
    public dateCreationDemande?: Moment,
    public lanque?: Language,
    public priorite?: Priorite,
    public modalite?: Modalite,
    public typeActivite?: TypeActivite,
    public patient?: IPatient,
    public destinataire?: IDestinataire,
    public specialiteClinique?: ISpecClinique,
    public ensembre?: IDemandeDeService,
    public formulaireEvaluations?: IFormulaireEvaluation[]
  ) {}
}
