import {CanActivateFn, Router} from '@angular/router';
import {AuthService} from "../services/auth/auth.service";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
  let service = inject(AuthService);
  let router = inject(Router)
  if (service.isAuthenticated()) {
    return true;
  }
  else {
    router.navigate(['/login'])
    return false;
  }
};
