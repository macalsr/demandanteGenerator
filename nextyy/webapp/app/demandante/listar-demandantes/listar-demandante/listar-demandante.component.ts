import { Component, OnInit } from '@angular/core';
import { Acao } from "../../../util/acao";
import { SEXO_LISTAR, SexoOptions } from "../../../shared/enums/enum-sexo";
import { Demandante, Telefone } from "../../../entities/demandante/demandante.model";
import { SelectItem } from "primeng/api";
import { AlertService } from "../../../core/util/alert.service";
import { Router } from "@angular/router";
import { AuthServerProvider } from "../../../core/auth/auth-jwt.service";
import { UserService } from "../../../entities/user/user.service";
import { DemandanteService } from "../../../entities/demandante/demandante.service";
import { faSuperscript } from "@fortawesome/free-solid-svg-icons";
import { DemandanteFilter } from "../../../entities/demandante/demandante.filter";
import { MensagensUtil } from "../../../util/mensagens.util";
import {TelefoneService} from "../../../entities/demandante/telefone.service";

@Component({
  selector: 'jhi-listar-demandante',
  templateUrl: './listar-demandante.component.html',
  styleUrls: ['./listar-demandante.component.scss']
})
    [x: string]: any;
export class ListarDemandanteComponent implements OnInit {

  telefone: Telefone[] = [];
  demandante: Demandante[] = [];
  cidadeSelecionado: SelectItem[] = [];

  situacao = SexoOptions;

  acoes = Acao.options;


  cols = [
    { field: 'nmDemandante', text: true, header: 'Nome Demandante', sortField: 'nmDemandante.sort' },
    { field: 'cidade', text: true, header: 'Endereco', sortField: 'cidade.sort' },
    { field: 'sexo', text: true, header: 'Sexo', sortField: 'sexo.sort' },
    { field: 'telefone', text: true, header: 'Contato', sortField: 'telefone.sort' },
  ];

  constructor(
    protected demandanteService: DemandanteService,
    private alertService: AlertService,
    private router: Router,
    private demandanteFilter: DemandanteFilter,
    private authorizationService: AuthServerProvider,
    private telefoneService: TelefoneService

  ) {faSuperscript: demandanteService}

  preencherSortTable() {
    this.datatable.value = [
      { field: 'id', order: -1 }
    ];
  }
  ngOnInit(): void {
     this.preencherSortTable();
     this.demandanteService.findALl(this.demandante);

    }
  buscarComIdCrescente() {
      this.demandanteFilter = new DemandanteFilter();
      this.datatable.multiSortMeta = [
        { field: 'situacao', order: 1 }
      ];
    }

  buscarTodos() {
    this.demandanteFilter = new DemandanteFilter();
    this.preencherSortTable();
    this.demandanteService.findALl(this.demandante);
  }

  carregarSelectSexo(selectSexo: string) {
    if (selectSexo && selectSexo.length >= 1) {
      this.demandanteService.findByCpf(selectSexo).subscribe(res => {
        this.demandanteFilter.sexo = res;
      });
    }
  }

  navegarRotaIncluir() {
    const perfil = this.demandanteService.findALl(this.demandante);
    if(perfil) {
      this.router.navigate(['experiencias/minhas0-experiencias/incluir'])
      return
    }
  }

  navegarRotaAlterar(id: number) {
    this.router.navigate(['experiencias/minhas-experiencias/editar', id])
  }

  navegarRotaVisualizar(id: number) {
    this.router.navigate(['experiencias/minhas-experiencias/detalhar', id])
  }



  carregarSelectCidade(descricaoCidade: string) {
    if (descricaoCidade && descricaoCidade.length >= 1) {
      this.demandanteService.findByCpf(descricaoCidade).subscribe(res => {
        this.assuntoSelecionado = res;
      });
    }
  }

  carregarSelectResponsavel(descricaoResponsavel: string) {
    if (descricaoResponsavel && descricaoResponsavel.length >= this.TAMANHO_MINIMO_DESCRICAO) {
      this.demandanteService.buscarPorResponsavel(descricaoResponsavel).subscribe(res => {
        this.responsavelSelecionado = res;
      });
    }
  }

  carregarSelectCategoria(descricaoCategoria: string) {
    if (descricaoCategoria && descricaoCategoria.length >= this.TAMANHO_MINIMO_DESCRICAO) {
      this.demandanteService.buscarPorCategoria(descricaoCategoria).subscribe(res => {
        this.categoriaSelecionado = res;
      });
    }
  }

  private verificarSituacaoFiltro() {
    if (this.demandanteFiltro.situacao) {
      this.adicionarFiltroListarSituacoes();
    }
  }

  private adicionarFiltroListarSituacoes() {
    this.demandanteFiltro.situacao = StatusExperienciaOptions.map(situacao => situacao.value)
      .filter(e => e != this.demandanteFiltro.situacao).join(' ').trim();
  }

  userHasThisRole(role: string): boolean {
    return this.authorizationService.hasRole(role);
  }

  retornarNomeSemNomeDeGuerra(nomeMilitar: string): string {
    const nomes = nomeMilitar.split(' ');
    const ulltimoNome = nomes[nomes.length - 1];
    return nomeMilitar.replace(ulltimoNome, ' ');
  }

  retornarNomeGuerra(nomeMilitar: string): string {
    const nomes = nomeMilitar.split(' ');
    return nomes[nomes.length - 1];
  }
}
