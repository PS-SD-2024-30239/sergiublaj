import { Component, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute } from '@angular/router';
import { ChefService } from '../../../core/service/chef/chef.service';
import { ChefModel } from '../../../shared/models/chef.model';


@Component({
  selector: 'app-chef',
  templateUrl: './chef.component.html',
  styleUrl: './chef.component.scss',
})
export class ChefComponent implements OnInit {

  chef?: ChefModel;
  chefId?: string;

  constructor(private route: ActivatedRoute, private chefService: ChefService) {
  }

  ngOnInit(): void {
    this.getChefById();
  }

  private getChefById(): void {
    this.route.params
      .pipe(takeUntilDestroyed())
      .subscribe(response => {
        this.chefId = response['id'];

        this.chefService.getById(this.chefId || '')
          .pipe(takeUntilDestroyed())
          .subscribe(response => this.chef = response);
      });
  }
}
