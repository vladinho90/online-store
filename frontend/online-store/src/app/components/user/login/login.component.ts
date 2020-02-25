import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/models/user/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  errorMessage: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    if(this.userService.currentUserValue){
      this.router.navigate(['/profile']);
      return;
    }
  }

  login(){
    this.userService.login(this.user).subscribe(
      data => {
        this.router.navigate(['/profile']);
      }, error => {
        console.log(error);
        this.errorMessage = 'Username or password is incorrect';
      }
    );
  }
}
