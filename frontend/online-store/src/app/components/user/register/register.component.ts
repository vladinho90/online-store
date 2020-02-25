import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/models/user/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  errorMessage: string;

  constructor(private userService: UserService, private router: Router) {

  }

  ngOnInit(): void {
    if (this.userService.currentUserValue) {
      this.router.navigate(['/profile']);
      return;
    }
  }
  register() {
    this.userService.register(this.user).subscribe(
      data => {
        this.router.navigate(['login']);
      }, error => {
        if (error.status === 409) {
          this.errorMessage = 'Username is already exists!';
        } else {
          this.errorMessage = 'Email is already exists!';
        }
      }
    );
  }

}
