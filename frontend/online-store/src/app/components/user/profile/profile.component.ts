
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/models/user/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: User;

  constructor(private userService: UserService, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(JSON.parse(localStorage.getItem('currentUser')));
    console.log(this.currentUser = JSON.parse(localStorage.getItem('detailUser')));
  }

  ngOnInit(): void {
    if (!this.currentUser) {
      this.router.navigate(['/login']);
    }
  }

  logOut() {
    this.userService.logOut().subscribe(
      data => {
        this.router.navigate(['login']);
      }
    );
  }

}
