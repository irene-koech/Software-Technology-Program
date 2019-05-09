import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MaterialModule } from '../material.module';
import { AuthService } from '../services/auth.service';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { Product } from '../product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public isAdmin;
  products: Observable<Product[]>;

  constructor(private authService: AuthService, private db: AngularFireDatabase, private router: Router) {
    
    authService.isAdmin().subscribe(
      success => this.isAdmin = success
      );

      this.products = db.list<Product>('Products').valueChanges();
   }

  ngOnInit() {
  }

  addProduct(){
    this.router.navigate(['/add'])
  }
}
