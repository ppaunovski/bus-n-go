import {Component, OnInit} from '@angular/core';
import {ControlsService} from "../../../services/controls/controls.service";
import {ControlsResponse} from "../../../model/responses/ControlsResponse";
import {Router, RouterLink} from "@angular/router";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/core";
import {MatSelect, MatSelectChange} from "@angular/material/select";
import {RouteInstanceService} from "../../../services/route-instance/route-instance.service";
import {RouteInstanceResponse} from "../../../model/responses/RouteInstanceResponse";

@Component({
  selector: 'app-controls',
  standalone: true,
  imports: [
    RouterLink,
    MatButton,
    MatFormField,
    MatLabel,
    MatOption,
    MatSelect
  ],
  templateUrl: './controls.component.html',
  styleUrl: './controls.component.css'
})
export class ControlsComponent implements OnInit {
  controls: ControlsResponse[] = []
  instances: RouteInstanceResponse[] = []

  instanceId: number | undefined;

  constructor(private _controlsService: ControlsService,
              private _routeInstanceService: RouteInstanceService,
              private router: Router) {
  }

  ngOnInit() {
    this._controlsService.getControls().subscribe(controls => {
      this.controls = controls
    })


  }

  onClick() {
    this._routeInstanceService.getAll().subscribe(instances => this.instances = instances)
  }

  onInstancePick(event: MatSelectChange) {
    this.instanceId = event.value
  }

  onStart() {
    if (this.instanceId) {
      this._controlsService.start(this.instanceId).subscribe(response => {
        console.log(response);
        this.router.navigate(['/controls', response.id])
      })
    }
  }
}
