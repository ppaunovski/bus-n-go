import {Bus} from "../Bus";
import {Direction} from "../Direction";
import {Line} from "../Line";
import {UserResponse} from "./UserResponse";

export interface RouteInstanceResponse {
  bus: Bus,
  direction: Direction,
  line: Line,
  driver: UserResponse,
  start: Date,
  end: Date | undefined,
  id: number
}
