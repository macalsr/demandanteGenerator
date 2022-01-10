import { Component, Input, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';
import { Sexo } from '../../../../models/enumerations/sexo.model';
import { Endereco, IEndereco } from '../../../../models/endereco.model';
import { DemandanteService } from '../../../services/demandante.service';
import { TelefoneService } from '../../../services/telefone/telefone.service';
import { EnderecoService } from '../../../services/endereco/endereco.service';
import { ITelefone } from '../../../../models/telefone.model';
import { Demandantes, IDemandante } from '../../../../models/demandante.model';

@Component({
  selector: 'demandate-update',
  templateUrl: './demandante-update.component.html',
})
export class DemandanteUpdateComponent implements OnInit {
  isSaving = false;
  sexoValues = Object.keys(Sexo);
  telefonesCollection: ITelefone[] = [];
  enderecosCollection: IEndereco[] = [];
  endereco!: IEndereco;
  telefone!: ITelefone;

  constructor(
    protected demandanteService: DemandanteService,
    protected telefoneService: TelefoneService,
    protected enderecoService: EnderecoService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  // @ts-ignore
  editForm = this.fb.group({
    $id: [],
    nmDemandante: [null, [Validators.required]],
    cpf: [null, [Validators.required]],
    cdTipoDemandante: [],
    cdAtendimento: [],
    sexo: [],
    active: [],
  });

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandante }) => {
      this.updateForm(demandante);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demandante = this.createFromDemandante();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Demandantes>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected subscribeToSaveResponseTelefone(result: Observable<HttpResponse<ITelefone>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected subscribeToSaveResponseEndereco(result: Observable<HttpResponse<IEndereco>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {}

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(demandante: IDemandante): void {
    this.editForm.patchValue({
      id: demandante.id,
      nmDemandante: demandante.nmDemandante,
      cpf: demandante.cpf,
      cdTipoDemandante: demandante.cdTipoDemandante,
      cdAtendimento: demandante.cdAtendimento,
      sexo: demandante.sexo,
    });

    this.telefonesCollection = this.telefoneService.addTelefoneToCollectionIfMissing(this.telefonesCollection, demandante.telefone);
    this.enderecosCollection = this.enderecoService.addEnderecoToCollectionIfMissing(this.enderecosCollection, demandante.endereco);
  }

  protected loadRelationshipsOptions(): void {
    this.telefoneService
      .query({ filter: 'demandante-is-null' })
      .pipe(map((res: HttpResponse<ITelefone[]>) => res.body ?? []))
      .pipe(
        map((telefones: ITelefone[]) =>
          this.telefoneService.addTelefoneToCollectionIfMissing(telefones, this.editForm.get('telefone')!.value)
        )
      )
      .subscribe((telefones: ITelefone[]) => (this.telefonesCollection = telefones));

    this.enderecoService
      .query({ filter: 'demandante-is-null' })
      .pipe(map((res: HttpResponse<IEndereco[]>) => res.body ?? []))
      .pipe(
        map((enderecos: IEndereco[]) =>
          this.enderecoService.addEnderecoToCollectionIfMissing(enderecos, this.editForm.get('endereco')!.value)
        )
      )
      .subscribe((enderecos: IEndereco[]) => (this.enderecosCollection = enderecos));
  }

  protected createFromDemandante(): {
    telefone: string;
    endereco: string;
    cpf: any;
    cdTipoDemandante: any;
    cdAtendimento: any;
    id: any;
    sexo: any;
    nmDemandante: any;
  } {
    return {
      endereco: this.endereco.uf.concat(' ').concat(this.endereco.bairro.concat('').concat(this.endereco.localidade)),
      telefone: this.telefone.ddd.concat('').concat(this.telefone.telefone),
      id: this.editForm.get(['id'])!.value,
      nmDemandante: this.editForm.get(['nmDemandante'])!.value,
      cpf: this.editForm.get(['cpf'])!.value,
      cdTipoDemandante: this.editForm.get(['cdTipoDemandante'])!.value,
      cdAtendimento: this.editForm.get(['cdAtendimento'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
    };
  }
}
