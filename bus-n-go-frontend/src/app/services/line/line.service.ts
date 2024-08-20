import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Line} from "../../model/Line";
import {Direction} from "../../model/Direction";
import {LineDirection} from "../../model/LineDirection";

@Injectable({
  providedIn: 'root'
})
export class LineService {
  private url = '/api/lines'
  constructor(private _http: HttpClient) { }

  getAll(): Observable<Line[]> {
    return this._http.get<Line[]>(`${this.url}`)
  }

  getDirections(line: Line): Observable<LineDirection[]> {
    return this._http.get<LineDirection[]>(`${this.url}/${line.id}/directions`)
  }
}
