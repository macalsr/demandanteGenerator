import {Component, Input, OnInit} from '@angular/core';
import { AbstractControl, ControlValueAccessor, FormBuilder, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { Component, forwardRef, Input, OnInit, ViewChild } from '@angular/core';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {Cidade, Demandante, Telefone} from "../../../entities/demandante/demandante.model";
import {DemandanteFilter} from "../../../entities/demandante/demandante.filter";
import {Acao} from "../../../util/acao";
import {User} from "../../../shared/user.model";

@Component({
  selector: 'jhi-form-demandante',
  templateUrl: './form-demandante.component.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FormDemandanteComponent),
      multi: true
    }
  ]
})
export class FormDemandanteComponent implements  OnInit, ControlValueAccessor{

  @BlockUI() blockUI!: NgBlockUI;
  @Input() demandante: Demandante = new DemandanteFilter();
  @Input() demandante: Demandante = new class implements Demandante {
    cdAtendimento: string;
    cidade: Cidade;
    cpf: string;
    email: string;
    id: number;
    nmdemandante: string;
    telefone: Telefone;
    user: User;
  };

  acoes = Acao.options;
  nmDemandante: string;
  cpf: string;
  telefone = elefone
  user = new User();


}
