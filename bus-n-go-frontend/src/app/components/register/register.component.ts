import { Component } from '@angular/core';
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {
  AbstractControl,
  Form,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";
import {RegisterRequest} from "../../model/requests/RegisterRequest";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    MatFormField,
    MatInput,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    name  : new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required])
  },
    {
      validators: this.matchValidator('password', 'confirmPassword')
    })

  constructor(private authService: AuthService, private router: Router) {
  }

  matchValidator(controlName: string, matchingControlName: string): ValidatorFn {
    return (abstractControl: AbstractControl) => {
      const control = abstractControl.get(controlName);
      const matchingControl = abstractControl.get(matchingControlName);

      if (matchingControl!.errors && !matchingControl!.errors?.['confirmedValidator']) {
        return null;
      }

      if (control!.value !== matchingControl!.value) {
        const error = { confirmedValidator: 'Passwords do not match.' };
        matchingControl!.setErrors(error);
        return error;
      } else {
        matchingControl!.setErrors(null);
        return null;
      }
    }


  }

  onSubmit() {
    this.authService.register(this.form.value as RegisterRequest).subscribe({
      next: response => {
        console.log(response)
        sessionStorage.setItem('jwt', response.token);
        this.authService.refreshAuth$.next(true);
        this.router.navigate(['/'])
      }
      }
    )
  }
}

