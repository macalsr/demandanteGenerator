
import { User, IUser } from './user-management.model';
import { UserManagementService } from './service/user-management.service';
import {DemandanteComponent} from "../../entities/entities/demandante/components/list/demandante.component";
import {
  DemandanteUpdateComponent
} from "../../entities/entities/demandante/components/form/update/demandante-update.component";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable, of} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({ providedIn: 'root' })
export class UserManagementResolve implements Resolve<IUser> {
  constructor(private service: UserManagementService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUser> {
    const id = route.params['login'];
    if (id) {
      return this.service.find(id);
    }
    return of(new User());
  }
}

export const userManagementRoute: Routes = [
  {
    path: '',
    component: DemandanteComponent,
    data: {},
  },
  {
    path: 'new',
    component: DemandanteUpdateComponent,
    resolve: {
      user: UserManagementResolve,
    },
  },
  {
    path: ':login/edit',
    component: DemandanteUpdateComponent,
    resolve: {
      user: UserManagementResolve,
    },
  },
];
