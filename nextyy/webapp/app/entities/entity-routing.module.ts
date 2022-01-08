import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarDemandantesModule } from "../demandante/listar-demandantes/listar-demandantes.module";
import { CriarDemandanteModule } from "../demandante/criar-demandante/criar-demandante.module";
import { DemandanteModule } from "../demandante/demandante.module";


const routes: Routes = [
  { path: 'listar-demandantes', loadChildren: () => ListarDemandantesModule},
  {path: 'criar-demandante', loadChildren: () => CriarDemandanteModule}
  ]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class EntityRoutingModule {}
