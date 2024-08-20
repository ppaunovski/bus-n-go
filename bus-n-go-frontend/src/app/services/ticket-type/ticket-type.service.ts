import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Tipbilet} from "../../model/Tipbilet";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TicketTypeService {

  private _url = '/api/ticket-types';

  constructor(private http: HttpClient) { }

  getTypes(): Observable<Tipbilet[]> {
    return this.http.get<Tipbilet[]>(`${this._url}`)
  }
}
