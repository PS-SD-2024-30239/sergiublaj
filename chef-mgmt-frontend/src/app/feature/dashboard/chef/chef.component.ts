import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { ChefService } from '../../../core/service/chef/chef.service';
import { ChefModel } from '../../../shared/models/chef.model';


@Component({
  selector: 'app-chef',
  templateUrl: './chef.component.html',
  styleUrl: './chef.component.css'
})
export class ChefComponent implements OnInit, OnDestroy {

  chef?: ChefModel;
  chefId?: string;
  queryParamSubscription?: Subscription;
  getByIdSubscription?: Subscription;

  constructor(private route: ActivatedRoute, private chefService: ChefService) {
  }

  ngOnInit(): void {
    this.getChefById();
  }

  ngOnDestroy(): void {
    this.unsubscribeFromSubscribers();
  }

  private getChefById(): void {
    this.queryParamSubscription = this.route.params.subscribe(response => {
      this.chefId = response['id'];

      this.getByIdSubscription = this.chefService.getById(this.chefId || '')
        .subscribe(response => this.chef = response);
    });
  }

  private unsubscribeFromSubscribers(): void {
    this.queryParamSubscription?.unsubscribe();
    this.getByIdSubscription?.unsubscribe();
  }

}
