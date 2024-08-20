import {Component, OnInit} from '@angular/core';
import {TicketService} from "../../services/ticket/ticket.service";
import {TicketResponse} from "../../model/responses/TicketResponse";
import {TicketComponent} from "../ticket/ticket.component";
import {RouterLink} from "@angular/router";
import {MatButton, MatButtonModule} from "@angular/material/button";

@Component({
  selector: 'app-tickets-page',
  standalone: true,
  imports: [
    TicketComponent,
    RouterLink,
    MatButtonModule
  ],
  templateUrl: './tickets-page.component.html',
})
export class TicketsPageComponent implements OnInit{

  tickets: TicketResponse[] = []

    constructor(private service: TicketService) {
    }

    ngOnInit(): void {
        this.service.getTickets().subscribe({
          next: response => {
            console.log(response);
            this.tickets = response
          }
        })
    }

}
