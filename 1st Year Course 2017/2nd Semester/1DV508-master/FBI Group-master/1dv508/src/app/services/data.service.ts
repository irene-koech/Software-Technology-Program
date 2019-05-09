import { Injectable } from '@angular/core';
import { AngularFireDatabase, AngularFireObject, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Order } from '../order';

import { MatSnackBar } from '@angular/material';


@Injectable()
export class DataService {

  productRef: Observable<Product[]>;
  orderRef: Observable<Order[]>;
  saleRef: Observable<Product[]>;
  user;
  caseRef: Observable<Product[]>;

  constructor(private db: AngularFireDatabase, private route: ActivatedRoute, private router: Router,
    public snackBar: MatSnackBar, public auth: AuthService) {
    this.auth.user$.subscribe(user => this.user = user);
  }

  getProduct(key: string) {
    this.productRef = this.db.list('/Products', ref => ref.orderByKey().equalTo(key))
      .snapshotChanges()
      .map(changes => {
        return changes.map(c => ({ key: c.payload.key, ...c.payload.val() }));
      });
    return this.productRef;
  }

  deleteProduct(key: string) {
    if (window.confirm('Do you want to delete this product?')) {
      this.db.list(`/Products`).remove(key);
      this.router.navigate(['/']);
      this.snackBar.open('Product deleted', 'OK', {
        duration: 2000,
      });
    }
  }

  updateProduct(key: string, product: Product) {
    if (window.confirm('Do you want to save changes?')) {
      this.db.list(`/Products`).update(key, product);
      this.router.navigate(['/details', encodeURIComponent(key)]);
      this.snackBar.open('Product updated', 'OK', {
        duration: 2000,
      });
    }
  }

  getOrder(key: string) {
    this.orderRef = this.db.list('/Orders', ref => ref.orderByKey().equalTo(key))
      .snapshotChanges()
      .map(changes => {
        return changes.map(c => ({ key: c.payload.key, ...c.payload.val() }));
      });
    return this.orderRef;
  }

  updateOrder(key: string, order: Order) {
    if (window.confirm('Do you want to save changes?')) {
      this.db.list(`/Orders`).update(key, order);
      this.snackBar.open('Order status updated', 'OK', {
        duration: 2000,
      });
    }
  }

  getOrderByUid(uid: string) {
    this.orderRef = this.db.list('/Orders', ref => ref.orderByChild('uid').equalTo(uid))
      .snapshotChanges()
      .map(changes => {
        return changes.map(c => ({ key: c.payload.key, ...c.payload.val() }));
      });
    return this.orderRef;
  }

  getDOTD(saleStatus: boolean) {
    this.saleRef = this.db.list('/Products', ref => ref.orderByChild('saleStatus').equalTo(saleStatus))
      .snapshotChanges()
      .map(changes => {
        return changes.map(c => ({ key: c.payload.key, ...c.payload.val() }));
      });
    return this.saleRef;
  }

  getUid(){
    if(this.user)
      return this.user.uid;
    else
      return 0;
  }

  // get products that is not DOTD
  getCase(Status: boolean) {
    this.caseRef = this.db.list('/Products', ref => ref.orderByChild('saleStatus').equalTo(Status))
      .snapshotChanges()
      .map(changes => {
        return changes.map(c => ({ key: c.payload.key, ...c.payload.val() }));
      });
    return this.caseRef;

  }

}


