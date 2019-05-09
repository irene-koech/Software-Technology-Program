import { Component, OnInit } from '@angular/core';
import { CurrentCategoryService } from '../services/current-category.service';
import { AngularFireDatabase } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import {BehaviorSubject} from 'rxjs/Rx';

@Component({
  selector: 'app-show-category',
  templateUrl: './show-category.component.html',
  styleUrls: ['./show-category.component.css']
})
export class ShowCategoryComponent implements OnInit {

  isAdmin;
  category: string;
  products: Observable<Product[]>

  constructor(private authService: AuthService, private db:AngularFireDatabase ,private cc: CurrentCategoryService, private router: Router) {

    authService.isAdmin().subscribe(
      success => this.isAdmin = success
      );

      this.cc.currentCategory.subscribe(
        category => {this.products = db.list<Product>('Products', ref =>
        ref.orderByChild('category').equalTo(category)).valueChanges();});
  }

  ngOnInit() {
  }

  addProduct(){
    this.router.navigate(['/add'])
  }
}
