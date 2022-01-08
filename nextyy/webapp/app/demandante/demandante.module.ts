import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, Routes } from "@angular/router";
import { ListarDemandantesModule } from "./listar-demandantes/listar-demandantes.module";
import { CriarDemandanteModule } from "./criar-demandante/criar-demandante.module";
import { ListarDemandanteComponent } from "./listar-demandantes/listar-demandante/listar-demandante.component";
import { CriarDemandanteComponent } from "./criar-demandante/criar-demandante/criar-demandante.component";
import { DemandanteComponent } from './demandante/demandante.component';

const routes: Routes = [
  {
    path: '',
    component: ListarDemandanteComponent
  },
  {
    path: 'incluir',
    component: CriarDemandanteComponent
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  declarations: [
    DemandanteComponent
  ]
})
export class DemandanteModule { }
