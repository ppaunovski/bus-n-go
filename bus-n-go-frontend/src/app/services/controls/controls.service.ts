import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ControlsResponse} from "../../model/responses/ControlsResponse";
import {FineResponse} from "../../model/responses/FineResponse";
import {FineRequest} from "../../model/requests/FineRequest";

@Injectable({
  providedIn: 'root'
})
export class ControlsService {
  private url = '/api/controls'

  constructor(private http: HttpClient) { }

  getControls(): Observable<ControlsResponse[]> {
    return this.http.get<ControlsResponse[]>(`${this.url}`)
  }

  start(routeInstanceId: number): Observable<ControlsResponse> {
    return this.http.put<ControlsResponse>(`${this.url}/start`, JSON.stringify(routeInstanceId), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }

  getControlInfo(controlId: number): Observable<FineResponse[]> {
    return this.http.get<FineResponse[]>(`${this.url}/${controlId}/fines`)
  }
}
