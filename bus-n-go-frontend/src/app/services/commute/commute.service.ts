import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StartCommuteRequest} from "../../model/requests/StartCommuteRequest";
import {Observable, tap} from "rxjs";
import {CommuteResponse} from "../../model/responses/CommuteResponse";
import {RouteInstanceResponse} from "../../model/responses/RouteInstanceResponse";

@Injectable({
  providedIn: 'root'
})
export class CommuteService {

  private url = '/api/commutes'

  constructor(private http: HttpClient) { }

  startCommute(request: StartCommuteRequest): Observable<CommuteResponse> {
    return this.http.put<CommuteResponse>(`${this.url}/start`, JSON.stringify(request), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }).pipe(
      tap(res => console.log(res))
    )
  }
}
