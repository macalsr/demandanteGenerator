import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DemandantesComponent} from "./components/demandantes/demandantes.component";
import {DemandantesFormComponent} from "./components/demandantes-form/demandantes-form.component";

const routes:Routes=[
  {path:'', redirectTo: 'list'},
  {path:'list', component: DemandantesComponent},
  {path:'create', component: DemandantesFormComponent},

]

@NgModule({
  imports:[RouterModule.forChild(routes)],
  exports:[RouterModule]
})
export class DemandantesRoutingModule { }
