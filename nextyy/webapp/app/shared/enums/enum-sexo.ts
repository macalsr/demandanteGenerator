import { SelectItem } from "primeng/api";

export class EnumSexo{
  static FEMININO = 'FEMININO';
  static MASCULINO = 'MASCULINO';
  static ABSTRATO = 'ABSTRATO';

}

export const SexoEnumMapper = {
  [EnumSexo.FEMININO]: 'FEMININO',
  [EnumSexo.MASCULINO]: 'MASCULINO',
  [EnumSexo.ABSTRATO]: 'ABSTRATO',

};

export const SexoOptions: SelectItem[] = [
  {value: null, label: 'Selecione'},
  { value: EnumSexo.FEMININO, label: SexoEnumMapper[EnumSexo.FEMININO] },
  { value: EnumSexo.MASCULINO, label: SexoEnumMapper[EnumSexo.MASCULINO] },
  { value: EnumSexo.ABSTRATO, label: SexoEnumMapper[EnumSexo.ABSTRATO] },

];
export const SEXO_LISTAR = SexoOptions.map(sexo => sexo.value).filter(e => e >= SexoOptions[2]).join(' ');
