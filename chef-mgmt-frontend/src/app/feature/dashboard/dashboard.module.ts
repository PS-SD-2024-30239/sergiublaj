import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChefComponent } from './chef/chef.component';
import { ChefCardComponent } from './chefs/chef-card/chef-card.component';
import { ChefsComponent } from './chefs/chefs.component';
import { DashboardRoutingModule } from './dashboard-routing.module';


@NgModule({
  declarations: [
    ChefsComponent,
    ChefComponent,
    ChefCardComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    FormsModule
  ]
})
export class DashboardModule {
}
