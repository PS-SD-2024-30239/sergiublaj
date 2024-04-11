import {CanActivateFn, Router} from "@angular/router";
import {inject} from "@angular/core";
import {UserModel} from "../../shared/models/user.model";


export const adminGuard: CanActivateFn = () => {
  const router = inject(Router);

  return getUser()?.role === 'ADMIN'
    ? true
    : router.navigateByUrl('/dashboard/invalid-access');
};

export const userGuard: CanActivateFn = () => {
  const router = inject(Router);

  return getUser()?.role === 'USER'
    ? true
    : router.navigateByUrl('/dashboard/invalid-access');
};


const getUser = (): UserModel => {
  return JSON.parse(localStorage.getItem('loggedUser') || '');
};
