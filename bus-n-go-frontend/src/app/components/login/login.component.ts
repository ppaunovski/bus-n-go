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
@Component({
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, RouterLink, InputFieldComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string | undefined;
  password: string | undefined;

  constructor(
    private router: Router,
    private service: AuthService
  ) {
  }

  onSubmit($event: Event) {
    console.log($event, this.email, this.password)
    if (this.email && this.password) {
      console.log('valid')
      this.service.login({
        email: this.email,
        password: this.password,
      }).subscribe({
        next: result => {
          console.log(result)
          this.service.refreshAuth$.next(true);
          sessionStorage.setItem('jwt', result.token);
        }
      })
    }

  }

  handleInputChange(key: string, value: string | undefined) {
    console.log(key, value)
    switch (key){
      case 'email':
        this.email = value;
        break
      case 'password':
        this.password = value;
        break
    }
  }
}

