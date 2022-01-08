export class Acao {
  static CADASTRAR = 'cadastrar';
  static VISUALIZAR = 'visualizar';
  static SUCESSO = 'sucesso';
  static CANCELAR = 'cancelar';

  static options = {
    CADASTRAR: Acao.CADASTRAR,
    VISUALIZAR: Acao.VISUALIZAR,
    SUCESSO: Acao.SUCESSO,
    CANCELAR: Acao.CANCELAR,
  };
}

export class AcoesInfo {
  title: string;
  icon: string;
  id: string;

  constructor(title: string, icon: string, id: string) {
    this.title = title;
    this.icon = icon;
    this.id = id;
  }
}

export enum AcaoOptionsEnum {
  VISUALIZAR_DEMANDANTE, VISUALIZAR_CIDADE, VISUALIZAR_TELEFONE, VISUALIZAR_SEXO,
  EDITAR_DEMANDANTE, EDITAR_CIDADE, EDITAR_TELEFONE, EDITAR_SEXO,
  EXCLUIR_DEMANDANTE, EXCLUIR_CIDADE, EXCLUIR_TELEFONE, EXCLUIR_SEXO,
  SUCESSO, CANCELAR, INCLUIR,
}

export const AcaoEnumMapper = {
  [AcaoOptionsEnum.VISUALIZAR_DEMANDANTE]: new AcoesInfo('Visualizar Experiência', 'pi pi-eye', 'visualizar'),
  [AcaoOptionsEnum.VISUALIZAR_CIDADE]: new AcoesInfo('Visualizar Publicação', 'pi pi-eye', 'visualizar'),
  [AcaoOptionsEnum.VISUALIZAR_TELEFONE]: new AcoesInfo('Visualizar Referência', 'pi pi-eye', 'visualizar'),
  [AcaoOptionsEnum.VISUALIZAR_SEXO]: new AcoesInfo('Visualizar Categoria', 'pi pi-eye', 'visualizar'),
  [AcaoOptionsEnum.EDITAR_DEMANDANTE]: new AcoesInfo('Editar Experiência', 'pi pi-pencil', 'editar'),
  [AcaoOptionsEnum.EDITAR_CIDADE]: new AcoesInfo('Editar Publicação', 'pi pi-pencil', 'editar'),
  [AcaoOptionsEnum.EDITAR_TELEFONE]: new AcoesInfo('Editar Referência', 'pi pi-pencil', 'editar'),
  [AcaoOptionsEnum.EDITAR_SEXO]: new AcoesInfo('Editar Categoria', 'pi pi-pencil', 'editar'),
  [AcaoOptionsEnum.EXCLUIR_DEMANDANTE]: new AcoesInfo('Excluir Experiência', 'pi pi-trash', 'excluir'),
  [AcaoOptionsEnum.EXCLUIR_CIDADE]: new AcoesInfo('Excluir Publicação', 'pi pi-trash', 'excluir'),
  [AcaoOptionsEnum.EXCLUIR_TELEFONE]: new AcoesInfo('Excluir Referência', 'pi pi-trash', 'excluir'),
  [AcaoOptionsEnum.EXCLUIR_SEXO]: new AcoesInfo('Excluir Categoria', 'pi pi-trash', 'excluir'),
  [AcaoOptionsEnum.SUCESSO]: new AcoesInfo('Sucesso', 'pi pi-check', 'sucesso'),
  [AcaoOptionsEnum.CANCELAR]: new AcoesInfo('Cancelar', 'pi pi-times', 'cancelar'),
  [AcaoOptionsEnum.INCLUIR]: new AcoesInfo('INCLUIR', 'pi pi-arrow-right', 'incluir'),
};

