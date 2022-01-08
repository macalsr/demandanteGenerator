import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/v1/cidade/');

   constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

   create(cidade: FormData): Observable<void> {
     return this.http.post<void>(this.resourceUrl, {
     });
   }
   findById(id: number): Observable<Cidade> {
     return this.http.get<Cidade>(`${this.resourceUrl}/${id}`);
   }

 }
