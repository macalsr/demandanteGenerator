import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { UserDto, UserForListDto, EditPasswordDto } from '@core/dto/security';
import { environment } from '@environments/environment';

const API = environment.api_nexty;

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor(private http: HttpClient) { }

  getUserByIdentifier(identifier: string) {
    identifier = identifier.replace(/[^\d]+/g, '');
    return this.http.get(`${API}/v1/security/user/byidentifier/${identifier}/`).toPromise();
  }

  getList(filter: any, config?: any): Promise<UserForListDto[]> {
    const params = new HttpParams({ fromObject: { ...filter, ...config } });

    return this.http
      .get<any>(`${API}/v1/security/user/`, { params })
      .toPromise()
      .then((res) => res as UserForListDto[]);
  }
  
  
  filter(data: any): Promise<UserForListDto[]> {
    let params = new HttpParams();

    this.emptyForm(data);

    if (data.selectedStatus === undefined || data.selectedStatus === '') {
      data.selectedStatus = '0';
    } else {
      data.selectedStatus = '' + data.selectedStatus;
    }

    data.identifier = data.identifier.replace(/[^\d]+/g, '');

    params = params.append('name', data.name);
    params = params.append('email', data.email);
    params = params.append('identifier', data.identifier);
    params = params.append('status',data.selectedStatus);
    params = params.append('filterName', data.selectedFilterName);
    params = params.append('filterEmail', data.selectedFilterEmail);

    return this.http
    .get<any>(`${API}/v1/security/user/filter/`, { params })
    .toPromise()
    .then((res) => res as UserForListDto[]);
  }

  getHistoric(id: number) {    
    return this.http.get(`${API}/v1/security/user/${id}/historic/`).toPromise();                                                                                                                                      
  } 

  getById(id: number, config?: any): Promise<UserDto> {
    return this.http
      .get<any>(`${API}/v1/security/user/${id}/`)
      .toPromise()
      .then((res) => res as UserDto);
  }
  
  getByEmail(email: String, config?: any): Promise<Number> {
    return this.http
      .get<any>(`${API}/v1/security/user/bycountemail/${email}/`)
      .toPromise()
      .then((res) => res as number);
  }

  create(data: any): Promise<any> {
    data.identifier = data.identifier.replace(/[^\d]+/g, '');
    return this.http.post<any>(`${API}/v1/security/user/`, data).toPromise();
  }

  update(id: number, data: any): Promise<any> {
    return this.http.put<any>(`${API}/v1/security/user/${id}/`, data).toPromise();
  }

  editPassword(id:number, data: EditPasswordDto){
    return this.http.put(`${API}/v1/security/user/${id}/editpassword/`, data).toPromise();
  }

  activate(id: number, justification: string): Promise<any> {
    return this.http
      .put<any>(`${API}/v1/security/user/${id}/activate/`, { justification })
      .toPromise();
  }

  inactivate(id: number, justification: string): Promise<any> {
    return this.http
      .put<any>(`${API}/v1/security/user/${id}/inactivate/`, { justification })
      .toPromise();
  }

  emptyForm(data: any): boolean {
    if (data.selectedStatus === undefined || data.selectedStatus === '') {
      data.selectedStatus = '0';
    } else {
      data.selectedStatus = '' + data.selectedStatus;
    }

    if (data.selectedFilterName === undefined){
      data.selectedFilterName = '1';
    }
    if (data.selectedFilterEmail === undefined){
      data.selectedFilterEmail = '1';
    }

    if (data.name != '' 
     || data.email != '' 
     || data.identifier != '' 
     || data.selectedStatus != '0'
    ) {
      return false;
    } else {
      return true;
    }
  }
}