import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';


// Material
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from '../material.module';
import { AddCategoryComponent } from '../add-category/add-category.component';
import { CurrentCategoryService } from '../services/current-category.service';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  

  categories: Observable<string[]>;
  public isLoggedIn;
  public isAdmin;

  constructor(private authService: AuthService, private router: Router, private db: AngularFireDatabase, private cc: CurrentCategoryService) {
    
    authService.isAdmin().subscribe(
    success => this.isAdmin = success
    );

    authService.isAuthenticated().subscribe(
      success => this.isLoggedIn = success
    );

    this.categories = db.list<string>('Categories').valueChanges();
   }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();    
    this.router.navigate(['/']);
  }

  addCategory() {
    this.router.navigate(['/addCategory']);
  }

  changeCategory(category: string){
    this.cc.currentCategory.next(category);
    this.router.navigate(['/showCategory']);
  }
}
