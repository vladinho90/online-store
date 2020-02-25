import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user';
import { Observable } from 'rxjs';


const API_URL = 'http://localhost:7000/api/admin/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  currentUser: User;
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.headers = new HttpHeaders({
      authorization: 'Bearer ' + this.currentUser.token,
      'Content-Type': 'application/json; charset=UTF-8'
    });
  }

  findAllUsers(): Observable<any>{
    return this.http.get(API_URL+"all",
    {
      headers: this.headers,
    });
  }
}
