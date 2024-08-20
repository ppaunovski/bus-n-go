import {Component, OnInit} from '@angular/core';
import {LineService} from "../../../services/line/line.service";
import {BusService} from "../../../services/bus/bus.service";
import {DirectionService} from "../../../services/direction/direction.service";
import {Bus} from "../../../model/Bus";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/core";
import {MatSelect, MatSelectChange} from "@angular/material/select";
import {filter, Subject, switchMap, tap} from "rxjs";
import {Line} from "../../../model/Line";
import {Direction} from "../../../model/Direction";
import {RouteInstanceService} from "../../../services/route-instance/route-instance.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth/auth.service";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

@Component({
  selector: 'app-start-route-instnace',
  standalone: true,
  imports: [
    MatButton,
    MatFormField,
    MatLabel,
    MatOption,
    MatSelect
  ],
  templateUrl: './start-route-instance.component.html',
  styleUrl: './start-route-instance.component.css'
})
export class StartRouteInstanceComponent implements OnInit {
  buses: Bus[] = []
  lines: Line[] = []
  directions: Direction[] = []
  isDriverFree = false

  bus: Bus | undefined
  line: Line | undefined
  direction: Direction | undefined

  private _busSelected$ = new Subject<Bus>();
  private _lineSelected$ = new Subject<Line>();

  constructor(
    private _lineService: LineService,
    private _busService: BusService,
    private _directionService: DirectionService,
    private _routeInstanceService: RouteInstanceService,
    private _router: Router,
    private _authService: AuthService
  ) {
  }

  ngOnInit(): void {

    this._authService.isDriverFree().pipe(
      tap(x => console.log(x)),
      tap(isDriverFree => this.isDriverFree = isDriverFree),
      filter(isDriverFree => isDriverFree === true),
      switchMap(_ => this._busService.getAllFree())
    ).subscribe({
      next: response => {
        console.log('free buses', response)
        this.buses = response
      }
    })

    this._busSelected$.pipe(
      switchMap(bus => this._lineService.getAll())
    ).subscribe({
      next: response => {
        this.lines = response
      }
    })
    this._lineSelected$.pipe(
      switchMap(line => this._lineService.getDirections(line))
    ).subscribe({
      next: response => {
        this.directions = response.map(x => x.pravec)
        console.log(response)
      }
    })
  }

  onBusPick($event: MatSelectChange) {
    this.bus = this.buses.find(bus => bus.id === $event.value)
    if (this.bus) this._busSelected$.next(this.bus)
  }

  onLinePick($event: MatSelectChange) {
    this.line = this.lines.find(line => line.id === $event.value)
    if (this.line) this._lineSelected$.next(this.line)
  }

  onDirectionPick($event: MatSelectChange) {
    this.direction = this.directions.find(direction => direction.id === $event.value)
  }

  onSubmit() {
    if (this.bus && this.line && this.direction)
      this._routeInstanceService.start({
        lineId: this.line.id,
        busId: this.bus.id,
        directionId: this.direction.id,
      }).subscribe({
        next: response => {
          this._router.navigate(['/route-instances', response.id])
        }
      })
  }
}
