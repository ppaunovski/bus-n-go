import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ControlsService} from "../../../services/controls/controls.service";
import {FineResponse} from "../../../model/responses/FineResponse";
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {UserService} from "../../../services/user/user.service";
import {UserResponse} from "../../../model/responses/UserResponse";
import {MatButton} from "@angular/material/button";
import {MatCheckbox} from "@angular/material/checkbox";
import {FineRequest} from "../../../model/requests/FineRequest";
import {FineService} from "../../../services/fine/fine.service";
import {Subject, switchMap} from "rxjs";

@Component({
  selector: 'app-control',
  standalone: true,
  imports: [
    FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatOption, MatSelect, MatButton, MatCheckbox
  ],
  templateUrl: './control.component.html',
  styleUrl: './control.component.css'
})
export class ControlComponent implements OnInit {
  controlId: number = +this.route.snapshot.paramMap.get('id')!!;
  fines: FineResponse[] = []
  passengers: UserResponse[] = []

  fineForm: FormGroup = new FormGroup({
    dokument: new FormControl(null, [Validators.required]),
    plateno: new FormControl(null, [Validators.required]),
    iznos: new FormControl(null, [Validators.required]),
    patnikId: new FormControl(null, []),
    telefon: new FormControl(null, []),
    ime: new FormControl(null, []),
    adresa: new FormControl(null, []),
    kontrolaId: new FormControl(this.controlId, []),
  })
  private refreshFines$ = new Subject<void>();

  constructor(private route: ActivatedRoute, private router: Router,
              private controlsService: ControlsService,
              private userService: UserService,
              private fineService: FineService) {
  }

  ngOnInit(): void {
    this.controlId = +this.route.snapshot.paramMap.get('id')!!
    this.refreshFines$.pipe(
      switchMap(() => this.controlsService.getControlInfo(this.controlId))
    ).subscribe({
      next: response => {
        this.fines = response
      }
    })
    this.refreshFines$.next()
  }


  onClick() {
    this.userService.getAllPassengers().subscribe(
      res => {
        this.passengers = res;
      }
    )
  }

  protected readonly onsubmit = onsubmit;

  onSubmit($event: any) {
    console.log($event)
    console.log(this.fineForm.value)
    this.fineService.createFine(this.fineForm.value as FineRequest).subscribe({
      next: res => {
        console.log(res)
        this.refreshFines$.next()
      }
    })
  }
}
