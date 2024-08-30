import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AdminUsersResponse} from "../../model/responses/AdminUsersResponse";
import {FinesPerLine} from "../../model/FinesPerLine";
import {CommutesByHour} from "../../components/charts/line-chart.component";
import {IncomeData} from "../../components/charts/stacked-bar-chart.component";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private url = '/api/admin'
  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<AdminUsersResponse[]> {
    return this.http.get<AdminUsersResponse[]>(`${this.url}/all-users`)
  }

  updateRolesToUser(id: number, roles: string[]): Observable<AdminUsersResponse[]> {
    return this.http.post<AdminUsersResponse[]>(`${this.url}/update-roles/${id}`, JSON.stringify(roles), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }

  getFinesPerLine() {
    return this.http.get<FinesPerLine[]>(`${this.url}/fines-by-line`);
  }

  getCommutesByHour() {
    return this.http.get<CommutesByHour[]>(`${this.url}/commutes-by-hour`);
  }

  getTotalIncome() {
    return this.http.get<IncomeData[]>(`${this.url}/total-income`);
  }
}
