import {Component, OnInit} from '@angular/core';
import {MatSelectChange, MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {TicketTypeService} from "../../services/ticket-type/ticket-type.service";
import {Tipbilet} from "../../model/Tipbilet";
import {MatButtonModule} from '@angular/material/button';
import {TicketService} from "../../services/ticket/ticket.service";

@Component({
  selector: 'app-tickets-buy',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule],
  templateUrl: './tickets-buy.component.html',
  styleUrl: './tickets-buy.component.css'
})
export class TicketsBuyComponent implements OnInit {
  types: Tipbilet[] = []
  selectedType: number | undefined;

  constructor(private ticketTypesService: TicketTypeService, private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.ticketTypesService.getTypes().subscribe({
      next: response => {
        console.log(response)
        this.types = response
      }
    })
  }

  protected readonly onsubmit = onsubmit;

  onSubmit() {
    if (this.selectedType)
      this.ticketService.buyTicket({
        type: this.selectedType,
      }).subscribe({
        next: response => {
          console.log(response)
        }
      })
  }

  onPick($event: MatSelectChange) {
    console.log($event)
    this.selectedType = $event.value
  }
}
