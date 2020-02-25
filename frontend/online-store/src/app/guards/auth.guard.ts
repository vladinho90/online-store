import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../models/user/user';
import { UserService } from '../services/user/user.service';

//the authentication guard is an angular root cart that's used to prevent unauthorized users from accessing restricted routs
//implements CanActivate intervace which enroll gurds to decide if our roads can be activated with can activate methfod
//if the method return true the road is activated so any law to proceed
//otherwise if the method returns false the route is blocked
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  currentUser: User;

  constructor(private router: Router, private userSerivice: UserService) {
    this.userSerivice.currentUser.subscribe(
      data => {
        this.currentUser = data;
      });
  }
  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.currentUser) {
      if (route.data.roles && route.data.roles.indexOf(this.currentUser.role) === -1) {
        this.router.navigate(['/401']);
        return false;
      }
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }

}
