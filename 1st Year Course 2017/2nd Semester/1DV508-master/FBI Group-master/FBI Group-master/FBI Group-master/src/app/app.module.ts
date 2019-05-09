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
import { CurrentCategoryService } from'./services/current-category.service';
// routes
import {Routes, RouterModule} from '@angular/router';

// bootstrap
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


// firebase
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule} from 'angularfire2/database';
import { environment } from '../environments/environment';

// auth service
import { AuthService } from '../app/services/auth.service';

// Forms
import { FormsModule } from '@angular/forms';
// import {FormControl, Validators} from '@angular/forms';


// Material
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { AppRoutingModule } from './routing.module';
import { ShowCategoryComponent } from './show-category/show-category.component';

// Auth guard
import { AuthGuard } from './services/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    CreateAccountComponent,    
    AddProductComponent, AdminLoginComponent, AddCategoryComponent, ShowCategoryComponent
  ],
  imports: [
    BrowserModule, NgbModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    // FormControl, Validators
  ],
  providers: [AuthService, CurrentCategoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
