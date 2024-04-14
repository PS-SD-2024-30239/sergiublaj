import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ChefService } from '../../../core/service/chef/chef.service';
import { ChefModel } from '../../../shared/models/chef.model';


@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrl: './chefs.component.scss',
})
export class ChefsComponent implements OnInit, OnDestroy {

  chefs: ChefModel[] = [];
  rating: number = 0;
  getAllSubscription?: Subscription;
  getAllByRatingSubscription?: Subscription;

  constructor(private chefService: ChefService, private router: Router) {
  }

  ngOnInit(): void {
    this.getChefs();
  }

  ngOnDestroy(): void {
    this.unsubscribeFromSubscribers();
  }

  searchAllByRating() {
    this.getAllByRatingSubscription = this.chefService.getAll(this.rating)
      .subscribe(response => this.chefs = response);
  }

  deleteChefCalled(chefId: string): void {
    console.log('Deleting chef ', chefId);
  }

  logOut(): void {
    this.clearCookies();
    localStorage.removeItem('loggedUser');
    this.router.navigateByUrl('/auth/login');
  }

  private unsubscribeFromSubscribers(): void {
    this.getAllSubscription?.unsubscribe();
    this.getAllByRatingSubscription?.unsubscribe();
  }

  private getChefs(): void {
    this.getAllSubscription = this.chefService.getAll()
      .subscribe({
        next: response => this.chefs = response,
        error: err => console.log(err),
      });
  }

  private clearCookies(): void {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i];
      const equalPos = cookie.indexOf('=');
      const name = equalPos > -1 ? cookie.slice(0, equalPos) : cookie;
      document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/;';
    }
  }
}
