import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { DataService } from './services/data.service';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import { Routes, RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule} from 'angularfire2/database';
import { environment } from '../environments/environment';
import { AuthService } from '../app/services/auth.service';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { AppRoutingModule } from './routing.module';
import { ShowCategoryComponent } from './show-category/show-category.component';
import { AuthGuard } from './services/auth.guard';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { FilterPipe } from './filter.pipe';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { CartService } from './services/cart.service';
import { StarComponent } from './star/star.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { EmailService } from './services/email.service';
import { HttpModule } from '@angular/http';
import { StarFilterPipe } from './star-filter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    CreateAccountComponent,
    AddProductComponent, AdminLoginComponent, AddCategoryComponent, ShowCategoryComponent, ProductDetailComponent, EditProductComponent,
    FilterPipe,
    ShoppingCartComponent,
    CheckoutComponent,
    StarComponent,
    MyProfileComponent,
    OrderDetailComponent,
    AdminPageComponent,
    StarFilterPipe,
  ],
  imports: [
    BrowserModule, NgbModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFirestoreModule,
    AngularFireAuthModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    HttpModule
    // FormControl, Validators
  ],
  providers: [AuthService, AuthGuard, DataService, CartService, EmailService],
  bootstrap: [AppComponent]
})
export class AppModule { }
