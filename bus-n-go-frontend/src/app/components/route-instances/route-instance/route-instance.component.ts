import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RouteInstanceService} from "../../../services/route-instance/route-instance.service";
import {RouteInstanceResponse} from "../../../model/responses/RouteInstanceResponse";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-route-instance',
  standalone: true,
  imports: [
    MatButton
  ],
  templateUrl: './route-instance.component.html',
})
export class RouteInstanceComponent implements OnInit {
  routeInstance: RouteInstanceResponse | undefined

  constructor(private route: ActivatedRoute,
              private routeInstanceService: RouteInstanceService,) {
  }

  ngOnInit(): void {
    const routeInstanceId: number = +this.route.snapshot.paramMap.get('id')!
    this.routeInstanceService.getById(routeInstanceId).subscribe({
      next: response => {
        this.routeInstance = response
      }
    })

  }

  endInstance() {
    if (this.routeInstance) {
      this.routeInstanceService.stop(this.routeInstance.id).subscribe({
        next: response => {
          this.routeInstance = response
        }
      })
    }
  }
}
