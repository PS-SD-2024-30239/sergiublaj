import {Routes} from '@angular/router';
import {loggedGuard, notLoggedGuard} from "./core/guard/authentication.guard";

export const routes: Routes = [
  {
    path: 'auth',
    canActivate: [notLoggedGuard],
    loadChildren: () => import('./feature/authentication/authentication.module').then(m => m.AuthenticationModule)
  },
  {
    path: 'dashboard',
    canActivate: [loggedGuard],
    loadChildren: () => import('./feature/dashboard/dashboard.module').then(m => m.DashboardModule)
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  },
];
