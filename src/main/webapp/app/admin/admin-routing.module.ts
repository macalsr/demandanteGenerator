import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        data: { pageTitle: 'nextyApp.demandante.home.title' },
        loadChildren: () => import('../entities/entities/demandante/demandante.module').then(m => m.DemandanteModule),
      },
      {
        path: 'configuration',
        loadChildren: () => import('./configuration/configuration.module').then(m => m.ConfigurationModule),
      },
      {
        path: 'logs',
        loadChildren: () => import('./logs/logs.module').then(m => m.LogsModule),
      },

    ]),
  ],
})
export class AdminRoutingModule {}
