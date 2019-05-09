import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { ShowCategoryComponent } from './show-category/show-category.component';

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: CreateAccountComponent},
    {path: 'add', component: AddProductComponent, /*canActivate: [AuthGuard]*/},
    {path: 'adminLogin', component: AdminLoginComponent},
    {path: 'addCategory', component: AddCategoryComponent, /*canActivate: [AuthGuard]*/},
    {path: 'showCategory', component: ShowCategoryComponent},
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
