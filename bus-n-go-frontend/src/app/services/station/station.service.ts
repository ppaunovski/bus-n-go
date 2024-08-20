import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Station} from "../../model/Station";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StationService {
  private url = '/api/stations'
  constructor(private http: HttpClient) { }

  getAllStations(): Observable<Station[]> {
    return this.http.get<Station[]>(this.url);
  }
}
