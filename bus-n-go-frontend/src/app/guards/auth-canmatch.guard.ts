import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";

export const authCanmatchGuard: CanActivateFn = (route, state) => {
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
