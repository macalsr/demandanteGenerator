import { EnumSexo } from "../../shared/enums/enum-sexo";

export class DemandanteFilter {
  nmdemandante?: string;
  cdAtendimento?: string;
  email?: string;
  cpf?:string;
  telefone?: string;
  cidade?: string;
  cep?: string;
  uf?: string;
  estado?: string;
  id?: number;
  ddd?: string;
  sexo?: EnumSexo;

}

