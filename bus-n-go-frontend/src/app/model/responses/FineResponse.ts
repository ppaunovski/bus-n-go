import {UserResponse} from "./UserResponse";
import {ControlsResponse} from "./ControlsResponse";

export interface FineResponse {
    id: number,
    iznos: number,
    plateno: boolean,
     dateCreated: Date,
    datePayed: Date | undefined,
    dokument: string,
     kondukter: UserResponse,
     kontrola: ControlsResponse,
     patnik: UserResponse | undefined,
    telefon: string | undefined,
    ime: string | undefined,
    adresa: string | undefined,
}
