import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import {ChefService} from "../../../core/service/chef/chef.service";
import {ChefModel} from "../../../shared/models/chef.model";

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrl: './chefs.component.css'
})
export class ChefsComponent implements OnInit, OnDestroy {

  chefs: ChefModel[] = [];
  rating: number = 0;
  getAllSubscription?: Subscription;
  getAllByRatingSubscription?: Subscription;

  constructor(private chefService: ChefService) {
  }

  ngOnInit(): void {
    this.getChefs();
  }

  ngOnDestroy(): void {
    this.unsubscribeFromSubscribers();
  }

  private unsubscribeFromSubscribers(): void {
    this.getAllSubscription?.unsubscribe();
    this.getAllByRatingSubscription?.unsubscribe();
  }

  private getChefs(): void {
    this.getAllSubscription = this.chefService.getAll()
      .subscribe({
        next: response => this.chefs = response,
        error: err => console.log(err)
      });
  }

  searchAllByRating() {
    this.getAllByRatingSubscription = this.chefService.getAll(this.rating)
      .subscribe(response => this.chefs = response);
  }

  deleteChefCalled(chefId: string): void {
    console.log('Deleting chef ', chefId);
  }
}
