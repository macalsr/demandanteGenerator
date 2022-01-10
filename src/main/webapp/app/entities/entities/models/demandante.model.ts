import { Sexo } from './enumerations/sexo.model';
import { ITelefone } from './telefone.model';
import { IEndereco } from './endereco.model';

export interface IDemandante {
  id: number;
  nmDemandante: string;
  cpf: string;
  cdTipoDemandante: number;
  cdAtendimento: number;
  sexo: Sexo;
  telefone: ITelefone;
  endereco: IEndereco;
}

export class Demandantes implements IDemandante {
  constructor(
    public id: number,
    public nmDemandante: string,
    public cpf: string,
    public cdTipoDemandante: number,
    public cdAtendimento: number,
    public sexo: Sexo,
    public telefone: ITelefone,
    public endereco: IEndereco
  ) {
  }
}

export function getDemandanteIdentifier(demandante: IDemandante):number {
  return demandante.id;
}
