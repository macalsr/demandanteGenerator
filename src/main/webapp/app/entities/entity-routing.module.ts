import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        data: { pageTitle: 'nextyApp.demandante.home.title' },
        loadChildren: () => import('./entities/demandante/demandante.module').then(m => m.DemandanteModule),
      },
    ]),
  ],
})
export class EntityRoutingModule {}
