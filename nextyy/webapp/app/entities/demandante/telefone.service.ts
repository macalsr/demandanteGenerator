import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TelefoneService {

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/v1/telefone/');

   constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

   create(telefone: FormData): Observable<void> {
     return this.http.post<void>(this.resourceUrl, {
     });
   }
   findById(id: number): Observable<Telefone> {
     return this.http.get<Telefone>(`${this.resourceUrl}/${id}`);
   }

 }
}
