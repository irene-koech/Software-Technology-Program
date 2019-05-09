import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Product } from '../product';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  categories: Observable<string[]>;
  products: AngularFireList<Product>;
  public user;
  saleStatus: boolean = false;
  salePrice: number = null;
  avgStar: number = null;
  

  constructor(public auth: AuthService, private router: Router, private db: AngularFireDatabase,public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.categories = this.db.list<string>('Categories').valueChanges();
    this.products = this.db.list<Product>('Products');
    this.auth.user$.subscribe(user => this.user = user);
  }

  addProduct(
    name: string, 
    brand: string, 
    category: string, 
    color: string, 
    price: number, 
    stock: number, 
    pictureURL: string,
  ) {
    this.products.push(new Product(name, brand, category, color, price, stock, pictureURL,this.saleStatus,this.salePrice, this.avgStar
    ));
    this.router.navigate(['/']);
    this.snackBar.open("product added", "OK", {
      duration: 2000,
    });
  }
}
