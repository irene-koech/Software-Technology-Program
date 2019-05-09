import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';
import {of} from 'rxjs/observable/of';
import { MatDialog } from '@angular/material'
import { CheckoutComponent } from '../checkout/checkout.component';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  isPopupOpened = true;
  totalPrice : number = 0;

  public shoppingCartItems$: Observable<Product[]> = of([]);
  public shoppingCartItems: Product[] = [];

  constructor(private cartSv: CartService, private dialog: MatDialog) {
  this.shoppingCartItems$ = this.cartSv.getItems();
  this.shoppingCartItems$.subscribe(_ => this.shoppingCartItems = _);
  this.countTotal();
}
  ngOnInit() {
    
  }


  openDialog(){
    const dialogRef = this.dialog.open(CheckoutComponent, {
      height: '700px', width: '1200px'
    })
  }
  
  countTotal(){
    var tPrice = 0;
    for(let product of this.shoppingCartItems){
      tPrice += +product.price * +product.stock;
    }
    this.totalPrice = tPrice
  }

}
