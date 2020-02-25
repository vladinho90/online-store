import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/user/admin.service';
import { User } from 'src/app/models/user/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  userList: Array<User>;
  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.findAllUsers();
  }
  findAllUsers() {
    this.adminService.findAllUsers().subscribe(
      data => {
        this.userList = data;
      }
    );
  }

  detail(user: User) {
    localStorage.setItem('detailUser', JSON.stringify(user));
    this.router.navigate(['detail', user.id]);
  }

}
