import {Component, OnInit} from '@angular/core';
import {RouteInstanceService} from "../../../services/route-instance/route-instance.service";
import {Station} from "../../../model/Station";
import {RouteInstanceResponse} from "../../../model/responses/RouteInstanceResponse";
import {StationService} from "../../../services/station/station.service";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/core";
import {MatSelect, MatSelectChange} from "@angular/material/select";
import {Subject, switchMap, tap} from "rxjs";
import {CommuteService} from "../../../services/commute/commute.service";
import {TicketService} from "../../../services/ticket/ticket.service";
import {TicketResponse} from "../../../model/responses/TicketResponse";
import {Router} from "@angular/router";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

@Component({
  selector: 'app-start-commute',
  standalone: true,
  imports: [
    MatButton,
    MatFormField,
    MatLabel,
    MatOption,
    MatSelect
  ],
  templateUrl: './start-commute.component.html',
  styleUrl: './start-commute.component.css'
})
export class StartCommuteComponent implements OnInit {
  tickets: TicketResponse[] = []
  stations: Station[] = []
  routeInstances: RouteInstanceResponse[] = []

  station: number | undefined;
  instance: number | undefined;
  ticket: number | undefined;

  private _stationSelected$ = new Subject<number>();
  private _instanceSelected$ = new Subject<number>();
  private _ticketSelected$ = new Subject<number>();
  private _startCommute$ = new Subject<void>();

  constructor(private routeInstanceService: RouteInstanceService,
              private stationService: StationService,
              private commuteService: CommuteService,
              private ticketService: TicketService,
              private _router: Router,
  ) {
  }

  ngOnInit(): void {
    this.ticketService.getTickets().subscribe({
      next: response => {
        this.tickets = response
        console.log(this.tickets)
      }
    })

    this.stationService.getAllStations().subscribe({
      next: response => {
        this.stations = response;
        console.log(this.stations)
      }
    })

    this._stationSelected$.pipe(
      switchMap(station => this.routeInstanceService.getForStation(station))
    ).subscribe({
      next: response => {
        this.routeInstances = response;
        console.log(response);
      }
    })
    // todo: finish logic
    this._startCommute$.pipe(
      switchMap(() => {
          console.log('in switch map');
          return this.commuteService.startCommute({
            stationId: this.station!!,
            routeInstanceId: this.instance!!,
            ticketId: this.ticket!!
          });
        }
      )
    ).subscribe({
      next: response => {
        console.log(response)
      },
      error: err => console.log(err)
    })
  }


  onStationPick(event: MatSelectChange) {
    this.station = event.value
    if (this.station) this._stationSelected$.next(this.station)
  }

  onInstancePick(event: MatSelectChange) {
    this.instance = event.value
    if (this.instance) this._instanceSelected$.next(this.instance)
  }

  startCommute() {
    console.log('starting commute');
    console.log('ticket', this.ticket);
    console.log('instance', this.instance);
    console.log('station', this.station);
    if (this.station && this.ticket && this.instance)
      this.commuteService.startCommute({
        stationId: this.station!!,
        routeInstanceId: this.instance!!,
        ticketId: this.ticket!!
      }).pipe(
        tap(res => console.log(res))
      ).subscribe(res => {
        console.log(res)
        this._router.navigate(['/commutes', res.id])
      })
  }

  onTicketSelect(event: MatSelectChange) {
    this.ticket = event.value
    if (this.ticket) this._ticketSelected$.next(this.ticket)
  }
}
