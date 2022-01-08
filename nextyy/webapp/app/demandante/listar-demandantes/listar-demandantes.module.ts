import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CriarDemandanteModule } from "../criar-demandante/criar-demandante.module";
import { EntityRoutingModule } from "../../entities/entity-routing.module";
import { DemandanteModule } from "../demandante.module";
import { ListarDemandanteComponent } from './listar-demandante/listar-demandante.component';



@NgModule({
  declarations: [
  
    ListarDemandanteComponent
  ],
  imports: [
    CommonModule,
    EntityRoutingModule,
    DemandanteModule

  ]
})
export class ListarDemandantesModule { }
