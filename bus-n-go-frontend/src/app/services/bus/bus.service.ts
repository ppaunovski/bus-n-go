import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Bus} from "../../model/Bus";

@Injectable({
  providedIn: 'root'
})
export class BusService {
  private _url = '/api/buses'
  constructor(private _http: HttpClient) { }

  getAll(): Observable<Bus[]> {
    return this._http.get<Bus[]>(`${this._url}`)
  }

  getAllFree() {
    return this._http.get<Bus[]>(`${this._url}/free`)
  }
}
