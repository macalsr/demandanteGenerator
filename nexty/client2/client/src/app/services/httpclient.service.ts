import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class Demandante{
  constructor(
    public id:string,
    public nmDemandante:string,
    public cpf:string,
    public email:string,
    public sexo:string,
    public telefone:string,
    public cidade: string
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) {
  }



  getDemandantesById() {
    let username='mmariasribeiro@gmail.com'
    let password='password'

    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });

    return this.httpClient.get<Demandante[]>('http://localhost:8080/v1/demandante/',{headers});
  }
  getDemandantesByCpf() {
    let username='mmariasribeiro@gmail.com'
    let password='password'

    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });

    return this.httpClient.get<Demandante[]>('http://localhost:8080/v1/demandante/cpf/',{headers});
  }

  public createDemandante() {
    let username='mmariasribeiro@gmail.com'
    let password='password'

    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.post<Demandante>("http://localhost:8080/v1/demandante/",{headers});
  }
}
