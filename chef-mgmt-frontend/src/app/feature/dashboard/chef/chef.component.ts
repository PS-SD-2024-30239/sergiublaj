import {Component, OnInit} from '@angular/core';
import {ChefModel} from "../../../shared/models/chef.model";
import {ChefService} from "../../../core/service/chef/chef.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-chef',
  templateUrl: './chef.component.html',
  styleUrl: './chef.component.css'
})
export class ChefComponent implements OnInit {

  chef?: ChefModel;
  chefId?: string;

  constructor(private route: ActivatedRoute, private chefService: ChefService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(response => {
      this.chefId = response['id'];

      this.chefService.getById(this.chefId || '')
        .subscribe(response => this.chef = response);
    });
  }
}
