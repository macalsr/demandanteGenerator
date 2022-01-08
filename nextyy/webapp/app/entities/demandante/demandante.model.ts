import { User } from "../user/user.model";

export interface Telefone {
  id?: number;
  ddd?: string;
  telefone: string;
}
export interface Cidade {
  id?: number;
  cidade?: string;
  cep?: string;
  uf?: string;
  estado?: string;


}
export interface Demandante {
  id?: number;
  nmdemandante?: string;
  cdAtendimento?: string;
  email?: string;
  cpf?:string;
  telefone?: Telefone;
  user?: User;
  cidade?: Cidade;

}

export function getDemandanteId(demandante: Demandante): number | undefined {
  return demandante.id;
}
