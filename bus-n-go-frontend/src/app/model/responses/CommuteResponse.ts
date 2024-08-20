import {TicketResponse} from "./TicketResponse";
import {UserResponse} from "./UserResponse";
import {LineStation} from "../LineStation";

export interface CommuteResponse {
commuter: UserResponse,
   ticket: TicketResponse,
   stationStart: LineStation,
   status: String,
   startDate: Date,
   endDate: Date | null,
   id: number,
}
