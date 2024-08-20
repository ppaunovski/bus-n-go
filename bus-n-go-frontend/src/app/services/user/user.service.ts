import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "../../model/responses/UserResponse";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _url= '/api/users'

  constructor(private _http: HttpClient) { }

  getUser(): Observable<UserResponse | undefined> {
    return this._http.get<UserResponse>(`${this._url}`)
  }

  getAllPassengers(): Observable<UserResponse[]> {
    return this._http.get<UserResponse[]>(`${this._url}/passengers`)
  }
}
