import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TicketBuyRequest} from "../../model/requests/TicketBuyRequest";
import {TicketResponse} from "../../model/responses/TicketResponse";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private _url = '/api/tickets'

  constructor(private http: HttpClient) { }

  getTickets(): Observable<TicketResponse[]> {
    return this.http.get<TicketResponse[]>(`${this._url}`)
  }

  activateTicket(id: number): Observable<TicketResponse> {
    return this.http.patch<TicketResponse>(`${this._url}/activate/${id}`, JSON.stringify({}), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }

  buyTicket(request: TicketBuyRequest): Observable<TicketResponse> {
    return this.http.post<TicketResponse>(`${this._url}/buy`, JSON.stringify(request), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }
}
