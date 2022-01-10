import { IDemandante } from './demandante.model';

export interface IEndereco {
  id: number;
  cep: string;
  logradouro: string;
  complemento: string;
  bairro: string;
  localidade: string;
  uf: string;
  demandante: IDemandante;
}

export class Endereco implements IEndereco {
  constructor(
    public id: number = 1,
    public cep: string,
    public logradouro: string,
    public complemento: string,
    public bairro: string,
    public localidade: string,
    public uf: string,
    public demandante: IDemandante
  ) {}
}

export function getEnderecoIdentifier(endereco: IEndereco): number {
  return endereco.id;
}
