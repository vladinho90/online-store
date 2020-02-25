<<<<<<< HEAD
import { Component } from '@angular/core';
import { User } from './models/user/user';
import { UserService } from './services/user/user.service';
import { Router } from '@angular/router';
import { Role } from './models/user/role.enum';
=======
import { Component, ChangeDetectorRef } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';
import { UserService } from './services/user.service';
import { User } from './common/user';
import { Address } from './common/address';
import { UrlSegment, ActivatedRoute } from '@angular/router';
//import { Address } from './common/address';
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  // template: `
  //   <button *ngIf="!isAuthenticated" (click)="login()"> Login </button>
  //   <button *ngIf="isAuthenticated" (click)="logout()"> Logout </button>
  //   <router-outlet></router-outlet>
  // `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-store';
<<<<<<< HEAD
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
=======
  isAuthenticated: boolean;
  user: User;

  constructor(public oktaAuth: OktaAuthService,
    private userService: UserService, private route: ActivatedRoute) {
    // Subscribe to authentication state changes
    this.oktaAuth.$authenticationState.subscribe(
      (isAuthenticated: boolean) => this.isAuthenticated = isAuthenticated);
  }

  async ngOnInit() {

    // Get the authentication state for immediate use
    this.isAuthenticated = await this.oktaAuth.isAuthenticated();

    console.log('is authenticated');
    if (this.isAuthenticated) {
      this.oktaAuth.getUser().then(userInfo => {
        this.userService.getUserByOktaUserId(userInfo.sub)
          .subscribe(
            user => {
              this.user = user;
              console.log('Found user');
              console.log(this.user);
            },
            errorResponse => {
              if (errorResponse.status === 404) {

                const user: User = new User(
                  userInfo.sub,
                  userInfo.displayName,
                  userInfo.emailAddress,
                  userInfo.role
                );
                this.userService.saveUser(user)
                  .subscribe(user => {
                    this.user = user;

                    console.log('Found user');
                    console.log(this.user);
                  },
                    _ => console.error('failed to save user')
                  );
              }
            }
          );
      });
    }



  }

  login() {
    this.oktaAuth.loginRedirect('/');
  }

  logout() {
    this.oktaAuth.logout('/');
  }

  handleProductDetails() {
    //get the "id" param string . convert string to a numbers using the + symbol
    const userName: string = this.route.snapshot.paramMap.get(`username`);

    this.userService.getUserByUsername(userName).subscribe(
      (data: User) => {
        this.user = data;


      });


>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
  }
}
