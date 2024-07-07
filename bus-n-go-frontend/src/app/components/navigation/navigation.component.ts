import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {Router, RouterLink} from "@angular/router";
import {switchMap} from "rxjs";
import {UserResponse} from "../../model/responses/UserResponse";
import {UserService} from "../../services/user/user.service";
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    RouterLink, MatButtonModule, MatMenuModule
  ],
  templateUrl: './navigation.component.html',
})
export class NavigationComponent implements OnInit {

  constructor(
    private _authService: AuthService,
    private _router: Router,
    private _userService: UserService,
  ) {
  }

  user: UserResponse | undefined

  ngOnInit(): void {
    this._authService.refreshAuth$.pipe(
      switchMap(auth => this._userService.getUser())
    ).subscribe({
      next: user => {
        this.user = user
      }
    })

    this._userService.getUser().subscribe({
      next: user => {
        this.user = user
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

  signOut() {
    sessionStorage.clear()
    this._authService.refreshAuth$.next(false)
  }
}
