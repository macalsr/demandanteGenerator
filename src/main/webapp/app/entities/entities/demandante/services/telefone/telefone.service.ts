import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { getTelefoneIdentifier, ITelefone } from '../../../models/telefone.model';
import { createRequestOption } from '../../../../../core/request/request-util';
import { isPresent } from '../../../../../core/util/operators';

export type EntityResponseType = HttpResponse<ITelefone>;
export type EntityArrayResponseType = HttpResponse<ITelefone[]>;

@Injectable({ providedIn: 'root' })
export class TelefoneService {
  constructor(protected http: HttpClient) {}
  protected resourceUrl = 'api/telefones';

  create(telefone: ITelefone): Observable<EntityResponseType> {
    return this.http.post<ITelefone>(this.resourceUrl, telefone, { observe: 'response' });
  }

  update(telefone: ITelefone): Observable<EntityResponseType> {
    return this.http.put<ITelefone>(`${this.resourceUrl}/${getTelefoneIdentifier(telefone) as number}`, telefone, { observe: 'response' });
  }

  partialUpdate(telefone: ITelefone): Observable<EntityResponseType> {
    return this.http.patch<ITelefone>(`${this.resourceUrl}/${getTelefoneIdentifier(telefone) as number}`, telefone, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITelefone>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITelefone[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTelefoneToCollectionIfMissing(telefoneCollection: ITelefone[], ...telefonesToCheck: (ITelefone | null | undefined)[]): ITelefone[] {
    const telefones: ITelefone[] = telefonesToCheck.filter(isPresent);
    if (telefones.length > 0) {
      const telefoneCollectionIdentifiers = telefoneCollection.map(telefoneItem => getTelefoneIdentifier(telefoneItem)!);
      const telefonesToAdd = telefones.filter(telefoneItem => {
        const telefoneIdentifier = getTelefoneIdentifier(telefoneItem);
        if (telefoneIdentifier == null || telefoneCollectionIdentifiers.includes(telefoneIdentifier)) {
          return false;
        }
        telefoneCollectionIdentifiers.push(telefoneIdentifier);
        return true;
      });
      return [...telefonesToAdd, ...telefoneCollection];
    }
    return telefoneCollection;
  }
}
