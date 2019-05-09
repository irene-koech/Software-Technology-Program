import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MaterialModule } from '../material.module';
import { AuthService } from '../services/auth.service';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  
  public categories: AngularFireList<string>;
  public isAdmin;
  
  constructor(private authService: AuthService, private router: Router, private db: AngularFireDatabase) { 
    authService.isAdmin().subscribe(
      success => this.isAdmin = success
      );

      this.categories = db.list('/Categories');
  }

  ngOnInit() {
  }
  
  addCategory(newCategory: string){
    this.categories.push(newCategory);
    this.router.navigate(['/'])
  }
}
