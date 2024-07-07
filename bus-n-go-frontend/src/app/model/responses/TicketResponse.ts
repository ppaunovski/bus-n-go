import {Tipbilet} from "../Tipbilet";

export interface TicketResponse {
  id: number,
  datumKupuvanje: Date,
  datumAktivacija: Date | undefined,
  status: string,
  tip: Tipbilet,
}
