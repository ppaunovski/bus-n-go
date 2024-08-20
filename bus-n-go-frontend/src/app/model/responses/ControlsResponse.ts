import {UserResponse} from "./UserResponse";
import {RouteInstanceResponse} from "./RouteInstanceResponse";

export interface ControlsResponse {
  id: number,
  dateCreated: Date,
  kondukter: UserResponse,
  instancaNaLinija: RouteInstanceResponse,
}
