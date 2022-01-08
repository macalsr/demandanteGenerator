import { Injectable } from "@angular/core";

@Injectable()
export class ObjectsUtil {

  static TIPO_STRING = 'string';

  static mapearFiltro(objetoFilter: any, filtro: any) {
    for (const key in filtro) {
      if (Object.prototype.hasOwnProperty.call(filtro, key)) {
        const objeto = objetoFilter[key];
        if (objeto?.label != null || objeto?.label != undefined) {
          const valueObjeto = objeto.label;
          filtro[key] = typeof valueObjeto === this.TIPO_STRING ? valueObjeto : valueObjeto.label;
        }
      }
    }
    return filtro;
  }
}
