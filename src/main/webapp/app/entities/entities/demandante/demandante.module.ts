import { DemandanteComponent } from './components/list/demandante.component';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { InputMaskModule } from 'primeng/inputmask';
import { CascadeSelectModule } from 'primeng/cascadeselect';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'primeng/api';
import { DemandanteUpdateComponent } from './components/form/update/demandante-update.component';
import { RouterModule } from '@angular/router';
import {userManagementRoute} from "../../../admin/user-management/user-management.route";

@NgModule({
  imports: [
    RouterModule.forChild(userManagementRoute),
    SharedModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CascadeSelectModule,
    InputMaskModule,
    InputTextModule,
    ButtonModule,
    TableModule,
    CascadeSelectModule,
    InputMaskModule,
  ],
  declarations: [DemandanteComponent, DemandanteUpdateComponent],
  entryComponents: [DemandanteComponent],
})
export class DemandanteModule {}
