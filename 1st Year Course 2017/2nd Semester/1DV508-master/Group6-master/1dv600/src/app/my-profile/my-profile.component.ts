import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { DataService } from '../services/data.service';
import { Order } from '../order';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AngularFirestore, AngularFirestoreDocument } from 'angularfire2/firestore';
import { User, Address } from '../user';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  orders: Order[];
  orderRef: AngularFireList<Order>;
  userRef: AngularFirestoreDocument<User>;
  user: User;
  uid: string;
  public hasEmail;
  public hasAddress;
  public userAddress: Address;
  public userEmail;

  constructor( 
    private data: DataService,
    private db: AngularFireDatabase, 
    private route: ActivatedRoute, 
    private auth: AuthService, 
    private afs: AngularFirestore,
    private snackBar : MatSnackBar,
    private router: Router,
  ) {
    this.route.paramMap.subscribe(params => {
      this.uid = params.get('userkey');
    });

  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if (params.has('user')) {
        this.uid = params.get('user');
        this.userRef = this.afs.doc(`Users/${params.get('user')}`);
      }
    });
    this.userRef.valueChanges().subscribe(user => {
      this.user = user;
      if (user.address) {
        this.hasAddress = true;
        this.userAddress = user.address;
      }
      if (user.email) {
        this.hasEmail = true;
        this.userEmail = user.email;
      }
    });
    
    this.data
    .getOrderByUid(this.uid)
    .subscribe(orders => {
      this.orders = orders;
    });
    
  }

  setAddress(name, address1, zip, city, email1) {
    const data: User = {
      uid: this.user.uid,
      email: email1,
      roles: this.user.roles,
      address: {
        name: name,
        address: address1,
        zip: zip,
        city: city
      }
    };
    this.snackBar.open('User details updated', 'OK', {
      duration: 2000,
    });
    return this.userRef.update(data);
  }
  
  selectOrder(key: string) {
    this.router.navigate(['/orderDetail', encodeURIComponent(key)]);
   }
}
