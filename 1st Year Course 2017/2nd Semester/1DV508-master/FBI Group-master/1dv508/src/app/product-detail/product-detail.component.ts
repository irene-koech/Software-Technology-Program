import { Component, OnInit } from '@angular/core';
import {
  AngularFireDatabase,
  AngularFireObject,
  AngularFireList
} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from '../services/data.service';
import { CartService } from '../services/cart.service';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
})
export class ProductDetailComponent implements OnInit {
  private user;

  product: Product;
  key: string;
  DOTD = false;

  constructor(
    public auth: AuthService,
    private db: AngularFireDatabase,
    private data: DataService,
    private route: ActivatedRoute,
    private router: Router,
    private cartSv: CartService,
    public snackBar: MatSnackBar
  ) {
    this.route.paramMap.subscribe(params => {
      this.key = params.get('productkey');
    });

    data
      .getProduct(this.key)
      .subscribe(product => {
        this.product = product[0];
      });

  }
  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);
  }


  onStock(stock: number) {
    if (stock > 0) {
      return true;
    } else {
      return false;
    }

  }

  deleteProduct() {
    this.data.deleteProduct(this.key);
  }

  editProduct() {
    this.router.navigate(['/editProduct', encodeURIComponent(this.key)]);
  }

  addToCart(item: Product) {
    if ((item.stock) > 0) {
      this.cartSv.addToCart(item);
      this.snackBar.open('Product added to cart', 'OK', {
        duration: 2000,
      });
    } else { alert('Insufficient stock'); }
  }
}
