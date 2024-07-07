import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthResponse} from "../../model/responses/AuthResponse";
import {AuthRequest} from "../../model/requests/AuthRequest";
import {RegisterRequest} from "../../model/requests/RegisterRequest";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  refreshAuth$ = new Subject<boolean>();
  private url = '/api/auth';

  constructor(private http: HttpClient) { }

  login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${this.url}`,
      JSON.stringify(authRequest),
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      }
    )
  }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${this.url}/register`,
      JSON.stringify(registerRequest),
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      }
    )
  }

  isAuthenticated(): Observable<boolean> {
    return this.http.get<boolean>(`${this.url}`);
  }

  isDriverFree(): Observable<boolean> {
    return this.http.get<boolean>(`${this.url}/is-driver-free`);
  }

}
