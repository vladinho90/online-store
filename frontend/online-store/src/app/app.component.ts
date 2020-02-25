import { Component } from '@angular/core';
import { User } from './models/user/user';
import { UserService } from './services/user/user.service';
import { Router } from '@angular/router';
import { Role } from './models/user/role.enum';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-store';
  currentUser: User;
  
 


  constructor(private userSerivce: UserService, private router: Router) {
    this.userSerivce.currentUser.subscribe(
      data => {
        this.currentUser = data;
      }
    );
  }

  logOut() {
    this.userSerivce.logOut().subscribe(
      data => {
        this.router.navigate(['/login']);
      }
    );
  }

  get isAdmin() {
    return this.currentUser && this.currentUser.role === Role.ADMIN;
  }
}
