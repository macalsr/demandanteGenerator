import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DemandantesComponent} from "./components/demandantes/demandantes.component";
import {DemandantesFormComponent} from "./components/demandantes-form/demandantes-form.component";
import {DemandantesRoutingModule} from "./demandantes-routing.module";


@NgModule({
  declarations: [
    DemandantesComponent,
    DemandantesFormComponent
  ],
  imports: [
    CommonModule,
    DemandantesRoutingModule
  ]
})
export class DemandantesModule { }
