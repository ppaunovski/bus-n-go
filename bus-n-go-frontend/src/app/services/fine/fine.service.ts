import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {FineResponse} from "../../model/responses/FineResponse";
import {FineRequest} from "../../model/requests/FineRequest";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FineService {
  private url = '/api/fines'

  constructor(private http: HttpClient) { }

  createFine(request: FineRequest): Observable<FineResponse> {
    return this.http.put<FineResponse>(`${this.url}`, JSON.stringify(request), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }
}
