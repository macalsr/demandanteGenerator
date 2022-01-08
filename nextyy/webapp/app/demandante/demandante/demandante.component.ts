import {Component, ViewChild} from '@angular/core';
import {DemandanteFilter} from "../../entities/demandante/demandante.filter";
import {SelectItem} from "primeng/api";
import {Table} from 'primeng/table';
import {SexoEnumMapper} from "../../shared/enums/enum-sexo";
import {AcaoEnumMapper} from "../../util/acao";
import {DemandanteService} from "../../entities/demandante/demandante.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {MensagensUtil} from "../../util/mensagens.util";
import {finalize} from "rxjs";
import {Demandante} from "../../entities/demandante/demandante.model";
import {ObjectsUtil} from "../../util/objectsUtil";

@Component({
  template: ``,
  selector: 'jhi-demandante'
})
export class DemandanteComponent {


  demandante!: Demandante;


  @BlockUI()
  blockUI!: NgBlockUI;

  @ViewChild("tabela", {static: true})
  datatable!: Table;

  demandanteFiltro: DemandanteFilter = new DemandanteFilter();

  sexo: SelectItem[] = [];


  situacaoMapper = SexoEnumMapper;

  acoesMenu: any[] = [];

  rows = 10;

  acaoMapper = AcaoEnumMapper;

  constructor(protected demandanteService: DemandanteService) { }


  findAll() {
    this.blockUI.start(MensagensUtil.BLOCKUI_CARREGANDO);
    const {pipe} = this.demandanteService.(this.demandanteFiltro, this.datatable);
    const {subscribe} = pipe(finalize(() => this.blockUI.stop()));
    subscribe((response: Demandante) => {
        this.demandante = response;
        this.demandanteFiltro = new DemandanteFilter();
      });
  }

  mapearFiltro(objetoFilter: any) {
    ObjectsUtil.mapearFiltro(objetoFilter, this.demandanteFiltro);
  }

}
