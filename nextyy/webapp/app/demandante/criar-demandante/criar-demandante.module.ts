import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CriarDemandanteComponent } from './criar-demandante/criar-demandante.component';
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ReactiveFormsModule} from "@angular/forms";
import { FormularioExperienciaComponent } from './formulario-demandante/formulario-experiencia.component';
import { FormDemandanteComponent } from './form-demandante/form-demandante.component';



@NgModule({
  declarations: [
    CriarDemandanteComponent,
    FormularioExperienciaComponent,
    FormDemandanteComponent
  ],
  imports: [
    CommonModule,
    ConfirmDialogModule,
    ReactiveFormsModule
  ]
})
export class CriarDemandanteModule { }
