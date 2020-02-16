import { UserService } from './user.service';
import { Component, OnInit } from '@angular/core';
import { IUser, User } from 'src/app/shared/model/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: IUser;
  
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = new User();
  }
  saveUser(){
    console.log(JSON.stringify(this.user));
     this.userService.saveUser(this.user)
     .subscribe(() => console.log('OK'),
     () => console.log('Could not save user'));
  }

}
