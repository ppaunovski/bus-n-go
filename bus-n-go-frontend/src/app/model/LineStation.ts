import {Line} from "./Line";
import {Station} from "./Station";
import {Direction} from "./Direction";

export interface LineStation {
  id: number,
  redenBroj: number,
  linija: Line,
  postojka: Station,
  pravec: Direction,
}
