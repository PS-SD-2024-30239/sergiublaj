import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { hasRole } from '../../core/guard/authorization.guard';
import { InvalidAccessComponent } from '../../shared/components/invalid-access/invalid-access.component';
import { NotFoundComponent } from '../../shared/components/not-found/not-found.component';
import { ChefComponent } from './chef/chef.component';
import { ChefsComponent } from './chefs/chefs.component';


export const routes: Routes = [
  {
    path: 'chefs',
    component: ChefsComponent,
    canActivate: [ hasRole ],
    data: {
      requiredRoles: [ 'ADMIN', 'USER' ],
    },
  },
  {
    path: 'chef/:id',
    component: ChefComponent,
    canActivate: [ hasRole ],
    data: {
      requiredRoles: [ 'ADMIN' ],
    },
  },
  {
    path: 'invalid-access',
    component: InvalidAccessComponent,
  },
  {
    path: 'not-found',
    component: NotFoundComponent,
  },
  {
    path: '**',
    redirectTo: 'chefs',
  },
];

@NgModule({
  imports: [
    InvalidAccessComponent,
    NotFoundComponent,
    RouterModule.forChild(routes),
  ],
  exports: [ RouterModule ],
})
export class DashboardRoutingModule {
}
