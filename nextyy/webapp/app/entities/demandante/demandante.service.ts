import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { ApplicationConfigService } from "../../core/config/application-config.service";
import { Pagination } from "../../core/request/request.model";
import { Observable } from "rxjs";
import { getUserIdentifier, IUser } from "../user/user.model";
import { createRequestOption } from "../../core/request/request-util";
import { isPresent } from "../../core/util/operators";
import { Demandante } from "./demandante.model";

@Injectable({
  providedIn: 'root'
})
export class DemandanteService {

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/v1/demandante/');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  findALl( demandante: Demandante[] ): Observable<Demandante> {
    return this.http.get<Demandante>(`${this.resourceUrl}/`);
  }
  create(experiencia: FormData): Observable<void> {
    return this.http.post<void>(this.resourceUrl, {
    });
  }
  findByCpf(cpf: string): Observable<Demandante> {
    return this.http.get<Demandante>(`${this.resourceUrl}/${cpf}`);
  }


}
