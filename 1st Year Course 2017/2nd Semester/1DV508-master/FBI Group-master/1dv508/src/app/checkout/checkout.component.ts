import { Component, OnInit, NgModule } from '@angular/core';
import { MaterialModule } from '../material.module';
import { AngularFirestore, AngularFirestoreDocument } from 'angularfire2/firestore';
import { AuthService } from '../services/auth.service';
import { User, Address } from '../user';
import { CartService } from '../services/cart.service';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import { of } from 'rxjs/observable/of';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { DataService } from '../services/data.service';
import { Order } from '../order';
import { getAnyKey } from '@firebase/util';
import { ProductDetailComponent } from '../product-detail/product-detail.component';
/* import { ChildChangeAccumulator } from '@firebase/database/dist/src/core/view/ChildChangeAccumulator'; */
import { EmailService } from '../services/email.service';
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
@NgModule({
  providers: [EmailService],
})
export class CheckoutComponent implements OnInit {
  public user;
  public hasAddress;
  public userEmail;
  public userAddress: Address;
  displayedColumns = ['product name', 'quatity', 'price'];
  public shoppingCartItems$: Observable<Product[]> = of([]);
  public shoppingCartItems: Product[] = [];
  public totalPrice = 0;

  constructor(public auth: AuthService, private afs: AngularFirestore, private cartSv: CartService, private db: AngularFireDatabase,
    private router: Router, public snackBar: MatSnackBar, private data: DataService, private mail: EmailService,
    private route: ActivatedRoute) {
    this.shoppingCartItems$ = this
      .cartSv
      .getItems();

    this.shoppingCartItems$.subscribe(_ => this.shoppingCartItems = _);

    this.countTotal();
  }


  ngOnInit() {
    this.auth.user$.subscribe(data => {
      this.user = data;
      if (data.address) {
        this.hasAddress = true;
        this.userAddress = data.address;
        this.userEmail = data.email;
      }
    });
  }

  setAddress(name, address1, zip, city) {
    const userRef: AngularFirestoreDocument<any> = this.afs.doc(`Users/${this.user.uid}`);
    const data: User = {
      uid: this.user.uid,
      email: this.user.email,
      roles: this.user.roles,
      address: {
        name: name,
        address: address1,
        zip: zip,
        city: city
      }
    };
    return userRef.set(data, { merge: true });
  }

  countTotal() {
    for (const product of this.shoppingCartItems) {
      this.totalPrice += +product.price * +product.stock;
    }
  }

  addOrder() {
    var key: string;
    this.db.list('/Orders').push(new Order(this.user.uid, 'New', this.shoppingCartItems));
    this.cartSv.clearCart();
    // this.mail.sendEmail(this.userEmail);
    /*   var orderId = this.data.getOrderId();
      var status = null;
      var orders = {};
      var _orders = {};
      var order = {
        order_id: orderId,
        products: this.shoppingCartItems,
        order_status: status
      };
  
      _orders["Order_" + orderId] = order;
      orders["orders"] = _orders
  
      this.afs.collection("Users").doc(this.user.uid).set(orders, { merge: true });*/
    this.router.navigate(['/']);

    this.snackBar.open('Order completed!', 'OK', {
      duration: 2000,
    });
  }

  stockChange() {

    var key: string;
    var newStock: number;
    var n = 0;
    var k;

    this.route.paramMap.subscribe(params => {
      key = params.get('productkey');
    });

    for (; this.shoppingCartItems[n]!=null; n++) {

      const item = this.shoppingCartItems[n];
      const product = this.db.object<Product>(key).valueChanges();
      /*var stock = productRef.subscribe(product => {
        return product.stock
      });*/
      for (k = 0; product[k]!=null; k++) {
        if (product[k].name === item.name) {

          if(newStock==0){
            this.snackBar.open('Sorry, already sold out', 'OK', {
              duration: 2000,
            });

            break;
          }

          else{
          newStock = product[k].stock - this.shoppingCartItems[n].stock;
          this.data.updateProduct(key, new Product(item.name, item.brand, item.category, item.color,
            item.price, newStock, item.pictureURL, item.saleStatus, item.salePrice, item.avgStar));

            this.snackBar.open('order completed!', 'OK', {
              duration: 2000,
            });
          
          }
        }
      }
      //key-->call value as #
      /*(const sProduct of this.shoppingCartItems){
        stock -= sProduct.stock;
      }*/
      /*or update
      compare key value of products
      extract the value as # */
    }
  }
}
