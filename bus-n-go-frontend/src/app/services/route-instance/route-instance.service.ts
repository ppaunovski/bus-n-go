import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {StartRouteInstanceRequest} from "../../model/requests/StartRouteInstanceRequest";
import {RouteInstanceResponse} from "../../model/responses/RouteInstanceResponse";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StartCommuteRequest} from "../../model/requests/StartCommuteRequest";
import {CommuteResponse} from "../../model/responses/CommuteResponse";

@Injectable({
  providedIn: 'root'
})
export class RouteInstanceService {
  private url = '/api/route-instances'

  constructor(private http: HttpClient) {
  }

  start(request: StartRouteInstanceRequest): Observable<RouteInstanceResponse> {
    return this.http.post<RouteInstanceResponse>(`${this.url}/start`, JSON.stringify(request), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }

  getById(id: number): Observable<RouteInstanceResponse> {
    return this.http.get<RouteInstanceResponse>(`${this.url}/${id}`)
  }

  stop(id: number): Observable<RouteInstanceResponse> {
    return this.http.patch<RouteInstanceResponse>(`${this.url}/stop`, JSON.stringify(id), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }

  getForStation(id: number): Observable<RouteInstanceResponse[]> {
    return this.http.get<RouteInstanceResponse[]>(`${this.url}/station/${id}`)
  }

  getAll(): Observable<RouteInstanceResponse[]> {
    return this.http.get<RouteInstanceResponse[]>(`${this.url}`)
  }
}
