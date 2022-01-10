import { IDemandante } from './demandante.model';

export interface ITelefone {
  id: number;
  ddd: string;
  telefone: string;
  tipoTelefone: number;
  demandante: IDemandante;
}

export class Telefone implements ITelefone {
  constructor(
    public id: number = 1,
    public ddd: string,
    public telefone: string,
    public tipoTelefone: number,
    public demandante: IDemandante
  ) {}
}

export function getTelefoneIdentifier(telefone: ITelefone) {
  return telefone.id;
}
