import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/components/login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(!this.authService.isAuthenticated()){
      this.router.navigate(['/login']);
      return false
    }

    let role = route.data['role'];
    for(let i = 0; i <= role.length; i ++){
      if(this.authService.hasRole(role[i])){
        return true;
      }
    }
    this.router.navigate(['/home']);
    return false;
  }
  
}
