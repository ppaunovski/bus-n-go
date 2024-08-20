import {Component, Input} from '@angular/core';
import { TicketResponse } from '../../model/responses/TicketResponse';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {TicketService} from "../../services/ticket/ticket.service";
import {Router} from "@angular/router";
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';


@Component({
  selector: 'app-ticket',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './ticket.component.html',
})
export class TicketComponent {
  @Input() ticket: TicketResponse | undefined;

  constructor(private service: TicketService, private router: Router, private _snackBar: MatSnackBar) {
  }

  activateTicket() {
    if(this.ticket) {
      this.service.activateTicket(this.ticket.id).subscribe({
        next: () => {
          this.openSnackBar("Ticket successfully activated!", "Okay");
        }
      })
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
}
