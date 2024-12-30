import {CanActivateFn, Router} from '@angular/router';
import { inject } from '@angular/core';
import {AuthenticationService} from "./core/services/AuthService/authentication.service";

export const userAuthGuardGuard: CanActivateFn = () => {
  const router = inject(Router);
  const authService = inject(AuthenticationService);

  if (authService.getIsLoggedInValue() && authService.getLoggedInUser()?.role == 'admin' || authService.getLoggedInUser()?.role == 'user') {
    return true;
  }
  router.navigate(["/unauthorized"]);
  return false;
};
