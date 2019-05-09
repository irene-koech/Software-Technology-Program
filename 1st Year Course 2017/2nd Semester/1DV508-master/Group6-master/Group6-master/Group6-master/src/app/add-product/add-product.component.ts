import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Product } from '../product';



@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  categories: Observable<string[]>;
  products: AngularFireList<Product>;
  public isAdmin;

  constructor(private authService: AuthService, private router: Router, private db: AngularFireDatabase) {
    this.categories = db.list<string>('Categories').valueChanges();
    this.products = db.list<Product>('Products');

    authService.isAdmin().subscribe(
      success => this.isAdmin = success
      );
   }

  ngOnInit() {
  }
  
  addProduct(name: string, brand: string, category: string, color: string, price: string, pictureURL: string){    
    this.products.push(new Product(name, brand, category, color, price, pictureURL));
    this.router.navigate(['/'])
  }
}
