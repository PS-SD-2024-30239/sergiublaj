import {CanActivateFn, Router} from '@angular/router';
import {UserModel} from "../../shared/models/user.model";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = () => {
  const loggedUser: UserModel = JSON.parse(localStorage.getItem('loggedUser')!) as UserModel;
  const router = inject(Router);

  if (loggedUser.role === 'ADMIN') {
    return true;
  } else {
    router.navigateByUrl('/dashboard/invalid-access');
    return false;
  }
};
