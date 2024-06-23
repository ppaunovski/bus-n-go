import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "../../model/responses/UserResponse";
import * as url from "node:url";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _url= '/api/users'

  constructor(private _http: HttpClient) { }

  getUser(): Observable<UserResponse | undefined> {
    return this._http.get<UserResponse>(`${this._url}`)
  }
}
