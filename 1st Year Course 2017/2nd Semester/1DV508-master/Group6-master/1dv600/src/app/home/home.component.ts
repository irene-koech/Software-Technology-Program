import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MaterialModule } from '../material.module';
import { AuthService } from '../services/auth.service';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import { DataService } from '../services/data.service';
import { CartService } from '../services/cart.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public user;
  products: Product[];
  itemsRef: AngularFireList<Product>;
  term;
  saleStatus: boolean = true;
  productStatus: boolean = false;
  saleProducts: Product[];
  starValue;
  

  constructor(
    public auth: AuthService, 
    private db: AngularFireDatabase, 
    private router: Router, 
    private data: DataService, 
    private cartSv: CartService, 
    public snackBar: MatSnackBar,
    private route: ActivatedRoute, 
  ) { 
    
  }

  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);

    this.data
    .getCase(this.productStatus)
    .subscribe(product => {
      this.products = product;
    });
    // this.itemsRef = this.db.list<Product>('Products');
    // this.products = this.itemsRef.snapshotChanges().map(changes => {
    //      return changes.map(c => ({ key: c.payload.key, ...c.payload.val()}));
    //    });
    // this.products = this.products.map((array) => array.reverse());

    this.data
    .getDOTD(this.saleStatus)
    .subscribe(product => {
      this.saleProducts = product;
    });

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

  starHandler(num){
    this.starValue = num + "."
  }

}
