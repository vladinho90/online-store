import { Injectable } from '@angular/core';
import { IUser } from 'src/app/shared/model/user';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class UserService{

    public static  USERS_API="/api/USERS";

    constructor(private httpClient: HttpClient){

    }

    saveUser(user: IUser):Observable<IUser>{
        return this.httpClient.post<IUser>(UserService.USERS_API,user);
    }
}