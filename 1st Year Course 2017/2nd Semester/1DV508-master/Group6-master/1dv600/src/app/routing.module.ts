import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { AddProductComponent } from './add-product/add-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { ShowCategoryComponent } from './show-category/show-category.component';
import { AuthGuard } from './services/auth.guard';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { AdminPageComponent } from './admin-page/admin-page.component';



const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: CreateAccountComponent},
    {path: 'add', component: AddProductComponent, canActivate: [AuthGuard]},
    {path: 'editProduct/:productkey', component: EditProductComponent, canActivate: [AuthGuard]},
    {path: 'editProduct', component: EditProductComponent, canActivate: [AuthGuard]},
    {path: 'adminLogin', component: AdminLoginComponent},
    {path: 'addCategory', component: AddCategoryComponent, canActivate: [AuthGuard]},
    {path: 'showCategory/:category', component: ShowCategoryComponent},
    {path: 'details/:productkey', component: ProductDetailComponent},
    {path: 'shoppingCart', component: ShoppingCartComponent},
    {path: 'checkout', component: CheckoutComponent},
    {path: 'myProfile/:user', component: MyProfileComponent},
    {path: 'myProfile', component: MyProfileComponent},
    {path: 'orderDetail/:orderkey', component: OrderDetailComponent},
    {path: 'adminPage', component: AdminPageComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
