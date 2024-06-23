import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";
import {switchMap, tap} from "rxjs";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent implements OnInit{

  constructor(
    private _authService: AuthService,
    private _router: Router,
    private _userService: UserService,
  ) {
  }
  private _user: UserResponse | undefined

  ngOnInit(): void {
    this._authService.refreshAuth$.pipe(
      switchMap(auth => this._userService.getUser())
    ).subscribe({
      next: user => {
        this._user = user
      }
    })
  }

  isMenuOpen = false;
  isIconMenuOpen = false;

  changeMenuState() {
    console.log('changeMenuState');
    this.isMenuOpen = !this.isMenuOpen;
  }

  changeIconMenuState() {
    console.log('changeIconMenuState');
    this.isIconMenuOpen = !this.isIconMenuOpen;
  }

}
