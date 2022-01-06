import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DemandantesComponent} from "./demandantes/components/demandantes/demandantes.component";
import {AuthGaurdService} from "./services/auth-guard.service";
import {DemandantesFormComponent} from "./demandantes/components/demandantes-form/demandantes-form.component";
import {LoginComponent} from "./login/login/login.component";
import {LogoutComponent} from "./login/logout/logout.component";
import {HomeComponent} from "./home/home.component";
import {DemandantesRoutingModule} from "./demandantes/demandantes-routing.module";

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate:[AuthGaurdService]},
  { path: 'login', component: LoginComponent  },
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService] },
  { path: 'demandantes', component:DemandantesRoutingModule},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
