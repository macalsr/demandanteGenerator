import { Injectable } from '@angular/core';
import { Routes } from '@angular/router';

import { DemandanteComponent } from '../components/list/demandante.component';
import { DemandanteUpdateComponent } from '../components/form/update/demandante-update.component';
import { DemandanteService } from '../services/demandante.service';
import { IDemandante } from '../../models/demandante.model';

@Injectable({ providedIn: 'root' })
export class DemandanteRoutingModule {
  constructor(private service: DemandanteService) {}
}
export const demandanteRoute: Routes = [
  {
    path: '',
    component: DemandanteComponent,
    data: {
      defaultSort: 'id,asc',
    },
  },
  {
    path: 'new',
    component: DemandanteUpdateComponent,
  },
  {
    path: ':id/edit',
    component: DemandanteUpdateComponent,
  },
];
