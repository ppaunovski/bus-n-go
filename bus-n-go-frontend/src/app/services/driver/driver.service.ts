import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DriverService {
  private url = '/api/drivers'
  constructor() { }
}
