import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router} from '@angular/router';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '../material.module';
import { AddCategoryComponent } from '../add-category/add-category.component';
import { DataService } from '../services/data.service';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Product } from '../product';
import {of} from 'rxjs/observable/of';
import { CartService } from '../services/cart.service';
import { MatDialog } from '@angular/material';
import { LoginComponent } from '../login/login.component';
import { CreateAccountComponent } from '../create-account/create-account.component';
import { AdminPageComponent } from '../admin-page/admin-page.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  categories: Observable<string[]>;
  public user;

  public shoppingCartItems$: Observable<Product[]> = of([]);
  public shoppingCartItems: Product[] = [];

  constructor(
    public auth: AuthService, 
    private router: Router, 
    private db: AngularFireDatabase,
    private data: DataService, 
    private cartSv: CartService,
    private popup: MatDialog) {
      this.shoppingCartItems$ = this
      .cartSv
      .getItems();

    this.shoppingCartItems$.subscribe(_ => this.shoppingCartItems = _);
     }

  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);
    this.categories = this.db.list<string>('Categories').valueChanges();
  }

  logout() {
    this.auth.logout();
    // this.router.navigate(['/']);
  }

  addCategory() {
    this.router.navigate(['/addCategory']);
  }

  changeCategory(category: string) {
    this.router.navigate(['/showCategory', encodeURIComponent(category)]);
  }
  showCart() {
    this.router.navigate(['/shoppingCart']);
  }

  showProfile() {
    this.router.navigate(['/myProfile', encodeURIComponent(this.user.uid)]);
  }

  showOrderList(){
    this.router.navigate(['/adminPage']);
  }

  loginPopup(){
    const dialogRef = this.popup.open(LoginComponent, {
      height: '50%', width: '60%'
    })
  }

  registerPopup(){
    const dialogRef = this.popup.open(CreateAccountComponent, {
      height: '50%', width: '60%'
    })
  }

  // orderListPopup(){
  //   const dialogRef = this.popup.open(AdminPageComponent, {
  //     height: '50%', width: '60%'
  //   })
  // }
  
  // close(){
  //   this.dilogRef.close()
  // }
}
