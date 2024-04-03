import {Component, OnInit} from '@angular/core';
import {ChefService} from "../../../core/service/chef/chef.service";
import {ChefModel} from "../../../shared/models/chef.model";

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrl: './chefs.component.css'
})
export class ChefsComponent implements OnInit {

  chefs: ChefModel[] = [];
  rating: number = 0;

  constructor(private chefService: ChefService) {
  }

  ngOnInit(): void {
    this.getChefs();
  }

  private getChefs(): void {
    this.chefService.getAll()
      .subscribe({
        next: response => this.chefs = response,
        error: err => console.log(err)
      });
  }

  searchAllByRating() {
    this.chefService.getAll(this.rating)
      .subscribe(response => this.chefs = response);
  }

  deleteChefCalled(chefId: string): void {
    console.log('Deleting chef ', chefId);
  }
}
