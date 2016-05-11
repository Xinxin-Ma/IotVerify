import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../models/user';


@Injectable()
export class UserService {
  constructor (private http:Http) {}

  getUsers() {
    return this.http.get('http://localhost:8080/rest/user/users').map((res:Response) => res.json());
  }

  getUserByUserId(userId:number) {
    return this.http.post('http://localhost:8080/rest/user/userId', userId+'').map((res:Response) => res.json());
  }
}