import {Component, OnInit} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {AdminService} from "../../../services/admin/admin.service";
import {AdminUsersResponse} from "../../../model/responses/AdminUsersResponse";
import {MatCheckboxChange, MatCheckboxModule} from '@angular/material/checkbox';
import {MatButton} from "@angular/material/button";
import {PieChartComponent} from "../../charts/pie-chart/pie-chart.component";
import {map, Observable} from "rxjs";
import {PieChartValues} from "../../../model/PieChartValues";
import {CommutesByHour, LineChartComponent} from "../../charts/line-chart.component";
import {IncomeChartComponent, IncomeData} from "../../charts/stacked-bar-chart.component";

@Component({
  selector: 'app-admin-panel',
  standalone: true,
  imports: [MatTableModule, MatCheckboxModule, MatButton, PieChartComponent, LineChartComponent, IncomeChartComponent],
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css'
})
export class AdminPanelComponent implements OnInit {

  allRoles = ['ROLE_ADMIN', 'ROLE_DRIVER', 'ROLE_CONDUCTOR', 'ROLE_PASSENGER']

  users: AdminUsersResponse[] = []
  usersColumns: string[] = ['id', 'name', 'email', 'phoneNumber', 'address', 'roles', 'update'];

  constructor(private adminService: AdminService) {

  }

  ngOnInit() {
    this.adminService.getAllUsers().subscribe({
      next: data => {
        this.users = data
        console.log(data)
      }
    })
  }

  checked(role: string, roles: string[]): boolean {
    return roles.includes(role)
  }

  updateRoles(id: number) {
    console.log(id)
    const user = this.users.find(u => u.id === id)
    console.log(user)
    if (!user) return;
    this.adminService.updateRolesToUser(id, user.roles).subscribe({
      next: value => {
        console.log(value)
        this.users = value
      }
    })
  }

  onSelectRole(id: number, event: MatCheckboxChange) {
    console.log(event)
    const role = event.source.name
    if (!role) return;
    this.users.filter(u => u.id === id).forEach(
      u => {
        var index = u.roles.indexOf(role);
        if (index > -1) {
          u.roles.splice(index, 1);
        } else {
          u.roles.push(role);
        }
      }
    )
  }

  getFinesPerLine(): Observable<PieChartValues[]> {
    return this.adminService.getFinesPerLine()
                .pipe(
                    map(data => data.flatMap(finePerLine => Object({value: finePerLine.count, name: finePerLine.line.ime}))
                    )
                );
  }

  getCommutesByHour(): Observable<CommutesByHour[]> {
    return this.adminService.getCommutesByHour();
  }

  getTotalIncome(): Observable<IncomeData[]> {
    return this.adminService.getTotalIncome()
  }
}
