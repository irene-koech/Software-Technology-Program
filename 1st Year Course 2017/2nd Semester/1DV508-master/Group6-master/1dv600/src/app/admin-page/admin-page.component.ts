import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Order } from '../order';
import { AngularFireList, AngularFireDatabase } from 'angularfire2/database';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { OrderDetailComponent } from '../order-detail/order-detail.component';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  orders: Observable<Order[]>;
  orderRef: AngularFireList<Order>;
  public user;

  constructor(
    private db: AngularFireDatabase, 
    private router: Router, 
    private dialog: MatDialog,
    public auth: AuthService, 
  ) {
  }

  ngOnInit() {
    this.orderRef = this.db.list<Order>('Orders');
    this.orders = this.orderRef.snapshotChanges().map(changes => {
      return changes.map(c => ({ key: c.payload.key, ...c.payload.val() })
      );
    });
    this.auth.user$.subscribe(user => this.user = user);
  }

  selectOrder(key: string) {
    this.router.navigate(['/orderDetail', encodeURIComponent(key)]);
   }

  // need to be edit
  openDialog(key: string) {
    const dialogRef = this.dialog.open(OrderDetailComponent, {
      height: '700px', width: '1200px'
    });

  }
  selectUser(key: string) {
    this.router.navigate(['/myProfile', encodeURIComponent(key)]);
  }

}
