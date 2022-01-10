import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { getDemandanteIdentifier, IDemandante } from '../../models/demandante.model';
import { createRequestOption } from '../../../../core/request/request-util';
import { isPresent } from '../../../../core/util/operators';

export type EntityResponseType = HttpResponse<IDemandante>;
export type EntityArrayResponseType = HttpResponse<IDemandante[]>;

@Injectable({ providedIn: 'root' })
export class DemandanteService {
  protected resourceUrl = 'api/demandantes';

  constructor(protected http: HttpClient) {}

  create(demandante: IDemandante): Observable<EntityResponseType> {
    return this.http.post<IDemandante>(this.resourceUrl, demandante, { observe: 'response' });
  }

  update(demandante: IDemandante): Observable<EntityResponseType> {
    return this.http.put<IDemandante>(`${this.resourceUrl}/${getDemandanteIdentifier(demandante) as number}`, demandante, {
      observe: 'response',
    });
  }

  partialUpdate(demandante: IDemandante): Observable<EntityResponseType> {
    return this.http.patch<IDemandante>(`${this.resourceUrl}/${getDemandanteIdentifier(demandante) as number}`, demandante, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDemandante>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  findByCpf(cpf: string): Observable<EntityResponseType> {
    return this.http.get<IDemandante>(`${this.resourceUrl}/${cpf}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDemandante[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDemandanteToCollectionIfMissing(
    demandanteCollection: IDemandante[],
    ...demandantesToCheck: (IDemandante | null | undefined)[]
  ): IDemandante[] {
    const demandantes: IDemandante[] = demandantesToCheck.filter(isPresent);
    if (demandantes.length > 0) {
      const demandanteCollectionIdentifiers = demandanteCollection.map(demandanteItem => getDemandanteIdentifier(demandanteItem)!);
      const demandantesToAdd = demandantes.filter(demandanteItem => {
        const demandanteIdentifier = getDemandanteIdentifier(demandanteItem);
        if (demandanteIdentifier == null || demandanteCollectionIdentifiers.includes(demandanteIdentifier)) {
          return false;
        }
        demandanteCollectionIdentifiers.push(demandanteIdentifier);
        return true;
      });
      return [...demandantesToAdd, ...demandanteCollection];
    }
    return demandanteCollection;
  }
}
