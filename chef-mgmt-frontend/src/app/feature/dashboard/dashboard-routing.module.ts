import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from "../../shared/components/not-found/not-found.component";
import {ChefsComponent} from "./chefs/chefs.component";
import {ChefComponent} from "./chef/chef.component";
import {authGuard} from "../../core/guard/auth.guard";


export const routes: Routes = [
  {
    path: 'chefs',
    canActivate: [authGuard],
    component: ChefsComponent
  },
  {
    path: 'chef/:id',
    component: ChefComponent
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
