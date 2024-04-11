import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from "../../shared/components/not-found/not-found.component";
import {ChefsComponent} from "./chefs/chefs.component";
import {ChefComponent} from "./chef/chef.component";
import {adminGuard, userGuard} from "../../core/guard/authorization.guard";
import {InvalidAccessComponent} from "../../shared/components/invalid-access/invalid-access.component";


export const routes: Routes = [
  {
    path: 'chefs',
    canActivate: [adminGuard, userGuard],
    component: ChefsComponent
  },
  {
    path: 'chef/:id',
    canActivate: [adminGuard],
    component: ChefComponent
  },
  {
    path: 'invalid-access',
    component: InvalidAccessComponent
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {
}
