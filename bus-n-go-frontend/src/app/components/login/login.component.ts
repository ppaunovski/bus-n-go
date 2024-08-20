import { Component } from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
  FormsModule,
  ReactiveFormsModule, FormGroup,
} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ErrorStateMatcher} from "@angular/material/core";
import {Router, RouterLink} from "@angular/router";
import {InputFieldComponent} from "../input-field/input-field.component";
import {AuthService} from "../../services/auth/auth.service";
import {AuthRequest} from "../../model/requests/AuthRequest";
import {tap} from "rxjs";
@Component({
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, RouterLink, InputFieldComponent],
  templateUrl: './login.component.html',
})
export class LoginComponent {

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  })

  constructor(
    private router: Router,
    private service: AuthService
  ) {
  }

  onSubmit($event: Event) {
    console.log(this.form.value as AuthRequest)
      this.service.login(this.form.value as AuthRequest)
        .pipe(
          tap(result => {
            console.log(result)
            sessionStorage.setItem('jwt', result.token);
            this.service.refreshAuth$.next(true);
          })
        )
        .subscribe({
        next: result => {
          this.router.navigate(['/'])
        },
        error: error => {
          sessionStorage.removeItem('jwt');
        }
      })
  }
}

