import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import {AuthenticationService} from "./core/services/AuthService/authentication.service";
import { Router } from '@angular/router';

export const adminAuthGuard: CanActivateFn = () => {
  const router = inject(Router);
  const authService = inject(AuthenticationService);

  if (authService.getIsLoggedInValue() && authService.getLoggedInUser()?.role == 'admin') {
    return true;
  }
  router.navigate(["/unauthorized"]);
  return false;
};
