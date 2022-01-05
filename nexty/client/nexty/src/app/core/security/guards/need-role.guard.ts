import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class NeedRoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const roles = _.concat([], route?.data?.role ?? '');

    if (roles && roles.length) {
      const someRoleFound = _.some(roles, (role) => this.authService.roles.includes(role));

      if (!someRoleFound) {
        this.router.navigate(['/home']);
        return false;
      }
    }
    return true;
  }
}
