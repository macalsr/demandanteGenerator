import { TelefoneService } from '../../services/telefone/telefone.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DemandanteService } from '../../services/demandante.service';
import { Telefone } from '../../../models/telefone.model';
import { Endereco } from '../../../models/endereco.model';
import { SelectItem } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EnderecoService } from '../../services/endereco/endereco.service';
import { Table } from 'primeng/table';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Demandantes, IDemandante } from '../../../models/demandante.model';

@Component({
  selector: 'jhi-demandate',
  templateUrl: './demandante.component.html',
})
export class DemandanteComponent implements OnInit {
  isLoading = false;
  first = 0;
  rows = Demandantes.length;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  sexo: SelectItem[] = [];
  endereco? = Endereco;
  telefone? = Telefone;
  demandantes?: IDemandante[];
  demandanteLoad!: Demandantes;

  @ViewChild('tabela', { static: true })
  datatable!: Table;

  cols = [
    { field: 'nmDemandante', text: true, header: 'Nome', sortField: 'nmDemandante.sort' },
    { field: 'cpf', text: true, header: 'CPF', sortField: 'cpf.sort' },
    { field: 'cdTipoDemandante', text: true, header: 'Tipo de Demandante', sortField: 'cdTipoDemandante.sort' },
    { field: 'cdAtendimento', text: true, header: 'Atendimento', sortField: 'cdAtendimento.sort' },
    { field: 'sexo', text: true, header: 'Sexo', sortField: 'sexo' },
    { field: 'telefone', text: true, header: 'Telefone', sortField: 'responsavel.sort', options: this.telefone },
    { field: 'endereco', text: true, header: 'Endereco', sortField: 'endereco', options: this.endereco },
  ];

  constructor(
    protected demandanteService: DemandanteService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected telefoneService: TelefoneService,
    protected enderecoService: EnderecoService
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.demandanteService
      .query({
        page: pageToLoad - 1,
        size: this.rows,
      })
      .subscribe({
        next: (res: HttpResponse<IDemandante[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        error: () => {
          this.isLoading = false;
        },
      });
  }

  ngOnInit(): void {
    this.loadPage();
  }

  trackId(index: number, item: IDemandante): number {
    return item.id;
  }

  protected onSuccess(data: IDemandante[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.first = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/demandante']);
    }
    this.demandantes = data ?? [];
    this.ngbPaginationPage = this.page;
  }
}
