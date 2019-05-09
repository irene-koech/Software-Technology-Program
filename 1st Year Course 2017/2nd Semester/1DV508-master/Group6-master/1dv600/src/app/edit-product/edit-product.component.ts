import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../product';
import { DataService } from '../services/data.service';


@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  categories: Observable<string[]>;
  public user;
  key: string;
  product: Product;
  saleStatus: boolean = false;
  avgStar: number = null;

  constructor(
    public auth: AuthService,
    private router: Router,
    private db: AngularFireDatabase,
    private data: DataService,
    private route: ActivatedRoute,
  ) {

    this.categories = db.list<string>('Categories').valueChanges();

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
    // this.saleStatus = this.product.saleStatus;
  }

  editProduct(
    name: string, 
    brand: string, 
    category: string, 
    color: string, 
    price: number, 
    stock: number,
    pictureURL: string,
    salePrice: number,
  ) {
    this.data.updateProduct(this.key, 
      new Product(
        name, 
        brand, 
        category, 
        color, 
        price, 
        stock, 
        pictureURL,
        this.saleStatus,
        salePrice,
        this.avgStar
        ));
  }
  
}
