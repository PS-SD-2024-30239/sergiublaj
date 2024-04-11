import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {CookieService} from "ngx-cookie-service";

export const notLoggedGuard: CanActivateFn = () => {
  const router = inject(Router);

  return !isCookiePresent()
    ? true
    : router.navigateByUrl('dashboard/chefs');
};

export const loggedGuard: CanActivateFn = () => {
  const router = inject(Router);

  return isCookiePresent()
    ? true
    : router.navigateByUrl('auth/login');
};

const isCookiePresent = (): boolean => {
  const cookieService = inject(CookieService);

  return cookieService.check('jwt-token');
}
