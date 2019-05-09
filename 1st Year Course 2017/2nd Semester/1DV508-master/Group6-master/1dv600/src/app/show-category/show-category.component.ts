import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from '../services/data.service';
import { CartService } from '../services/cart.service';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-show-category',
  templateUrl: './show-category.component.html',
  styleUrls: ['./show-category.component.css']
})
export class ShowCategoryComponent implements OnInit {
  public user;
  category: string;
  products: Observable<Product[]>;

  constructor(public auth: AuthService, private db: AngularFireDatabase ,
    private data: DataService, private router: Router, private route: ActivatedRoute, private cartSv: CartService, public snackBar: MatSnackBar) {
      this.route.paramMap
      .subscribe(params => {
         this.products = db.list<Product>('Products', ref =>
        ref.orderByChild('category').equalTo(decodeURIComponent(params.get('category')))).snapshotChanges().map(changes => {
          return changes.map(c => ({ key: c.payload.key, ...c.payload.val()}));
        });
      });
        this.products = this.products.map((array) => array.reverse());
  }

  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);
    /*this.data.currentCategory.subscribe(
      category => {
        this.products = this.db.list<Product>('Products', ref =>
          ref.orderByChild('category').equalTo(category)).valueChanges().map((array) => array.reverse());
      }
    );*/
  }

  addProduct() {
    this.router.navigate(['/add']);
  }

  selectProduct(key: string) {
    this.router.navigate(['/details', encodeURIComponent(key)]);
  }
  onStock(stock: number) {
    if (stock > 0) {
      return true;
    } else {
      return false;
    }
  }

  addToCart(item: Product){
    this.cartSv.addToCart(item);
    this.snackBar.open("product added in cart", "OK", {
      duration: 2000,
    });
  }

}
