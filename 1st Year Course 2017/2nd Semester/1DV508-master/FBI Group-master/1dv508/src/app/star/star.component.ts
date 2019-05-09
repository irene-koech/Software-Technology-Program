import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { AngularFireList, AngularFireDatabase } from 'angularfire2/database';
import { AuthService } from '../services/auth.service';
import { Observable } from '@firebase/util';
import { AngularFirestore } from 'angularfire2/firestore';
import { tick } from '@angular/core/testing';
import { DataService } from '../services/data.service';
import { Product } from '../product';

export interface Star {
  userId: any;
  productKey: any;
  rating: number;
}

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.css']
})
export class StarComponent implements OnInit {
  starValue = 0;
  starRef;
  productKey: string;
  userId;
  stars;
  avgRating: Observable<any>;
  product: Product;
  avgStar: number;
  cStars;


  constructor(private route: ActivatedRoute, private afs: AngularFirestore, public auth: AuthService, private data: DataService, private db: AngularFireDatabase) { 
    this.route.paramMap.subscribe(params => {
      this.productKey = params.get('productkey');
    });
    this.userId = this.data.getUid();

    this.data.getProduct(this.productKey).subscribe(product =>{
      this.product = product[0];
    });
    
  }

  ngOnInit() {
    this.starRef = this.afs.doc(`stars/${this.userId}_${this.productKey}`).valueChanges();
    this.starRef.subscribe(star =>{
      if (!star){
        this.starValue = 0;
      }
      else{
      this.starValue = star.rating;
      }
    });
    this.stars = this.afs.collection('stars', ref => ref.where('productKey', '==', this.productKey)).valueChanges();

    this.avgRating = this.stars.map(arr => {
      const ratings = arr.map(v => v.rating)
      return ratings.length ? ratings.reduce((total, val) => total + val) / arr.length : 'not reviewed'
    });

     this.afs.collection('stars', ref => ref.where('productKey', '==', this.productKey)).valueChanges().subscribe(star => {
      this.cStars = star;
    }) 

  }

  starHandler(num){
    this.starValue=num;
    
    // Star document data
    const star: Star = { userId: this.userId, productKey:this.productKey, rating: this.starValue };

    // Custom doc ID for relationship
    const starPath = `stars/${star.userId}_${star.productKey}`;

    // Set the data, return the promise
    return this.afs.doc(starPath).set(star);
  }

  avgStarHandler(){
    var ratings = 0;
    for(let star of this.cStars){
      ratings += star.rating
    }

    var avgStar = (ratings/this.cStars.length).toFixed(2);

    this.db.list('/Products').update(this.productKey, {avgStar: avgStar});
  }
}
