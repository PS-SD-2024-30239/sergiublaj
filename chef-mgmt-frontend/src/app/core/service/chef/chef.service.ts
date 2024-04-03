import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ChefModel} from "../../../shared/models/chef.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChefService {

  constructor(private http: HttpClient) {
  }

  getById(chefId: string): Observable<ChefModel> {
    return this.http.get<ChefModel>(`chef/v1/${chefId}`);
  }

  getAll(rating: number = 0): Observable<ChefModel[]> {
    return this.http.get<ChefModel[]>('chef/v1/all3', {
      params: {
        rating
      }
    });
  }
}
