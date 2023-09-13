import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { AuthenticationResponse } from 'src/app/models/AuthenticationResponse';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate {

  constructor(private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    const loggedUser = localStorage.getItem('user');

    if (loggedUser) {
      const authReponse: AuthenticationResponse = JSON.parse(loggedUser);
      const token = authReponse.token;
      if (token) {
        const jwtHealper = new JwtHelperService();
        const isTokenNotExpired = !jwtHealper.isTokenExpired(token);
        if (authReponse.userDTO.roles) {
          const roleAdminFounded = authReponse.userDTO.roles.find(role => role === 'ROLE_ADMIN');
          if (isTokenNotExpired && roleAdminFounded) {
            return true;
          }
        }
      }
    }
    this.router.navigate(['forbiden'])
    return false;
  }
}
