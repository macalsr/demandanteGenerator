export class MensagensUtil {

  public static BLOCKUI_CARREGANDO = 'Carregando...';
  public static BLOCKUI_CANCELANDO = 'Cancelando...';
  public static BLOCKUI_IMPRIMINDO = 'Imprimindo...';
  public static BLOCKUI_AGUARDE = 'Aguarde...';
  public static BLOCKUI_EXCLUINDO = 'Excluíndo...';
  public static BLOCKUI_SALVANDO = 'Salvando...';
  public static BLOCKUI_ALTERANDO = 'Alterando...';
  public static BLOCKUI_RELATORIO = 'Gerando relatório...';
  public static BLOCKUI_ARQUIVANDO = 'Arquivando...';
  montarMensagem(parametro: string, mensagem: string) {
    return mensagem.replace('{}', parametro);
  }

}
