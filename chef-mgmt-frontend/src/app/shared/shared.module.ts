import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {InvalidAccessComponent} from "./components/invalid-access/invalid-access.component";


@NgModule({
  declarations: [
    NotFoundComponent,
    InvalidAccessComponent
  ],
  exports: [
    NotFoundComponent,
    InvalidAccessComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule {
}
