import { Injectable, OnInit } from '@angular/core';
import { forEach, extend } from '@firebase/util';
import { Product } from '../product';
import {BehaviorSubject, Observable, Subject, Subscriber} from 'rxjs';
import {of} from 'rxjs/observable/of';



@Injectable()
export class CartService {
  private itemsInCartSubject: BehaviorSubject<Product[]> = new BehaviorSubject([]);
  private itemsInCart: Product[] = [];



  constructor() {
    this.itemsInCartSubject.subscribe(_ => this.itemsInCart = _);
   
  }

  public addToCart(item: Product) {
    for (let product of this.itemsInCart){
      if(item.name == product.name){
        product.stock++;
        console.table(this.itemsInCart);
        return
      }
    }
      item.stock = 1;
      this.itemsInCartSubject.next([...this.itemsInCart, item]);
      console.table(this.itemsInCart);
  }

  public getItems(): Observable<Product[]> {
    return this.itemsInCartSubject;
  }

  public clearCart(){
    this.itemsInCartSubject = new BehaviorSubject([]);
    this.itemsInCart.length = 0;
    this.itemsInCartSubject.subscribe(_ => this.itemsInCart = _);
  }

 /*  private cartContent = new Object();

  // Add new item to cart
  public addItem(id: string) {
    if (this.cartContent[id]) {
      this.cartContent[id]++;
    } else {
      extend(this.cartContent, { [id]: 1 });
    }
    console.log(this.cartContent);
  }

  // Set new quantity of specific item
  public setQuantity(id: string, quantity: number) {
    if (quantity <= 0) {
      delete this.cartContent[id];
    } else {
      this.cartContent[id] = quantity;
    }
  }

  // Get quantity of specific item
  public getQuantity(id: string) {
    return this.cartContent[id];
  }

  // Return all item keys as string array
  public getItemKeys() {
    return Object.keys(this.cartContent);
  }

  // Returns full shopping cart object
  public getItems() {
    return Observable.create(this.cartContent);
  }

 */
}
