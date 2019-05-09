import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MaterialModule } from '../material.module';
import { AuthService } from '../services/auth.service';
import { AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AngularFireAuth } from 'angularfire2/auth';
import { User } from '@firebase/auth-types';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  private user;
  public categories: AngularFireList<string>;

  constructor(public auth: AuthService, private router: Router,
    private db: AngularFireDatabase, private afAuth: AngularFireAuth) { }

  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);
    this.categories = this.db.list('/Categories');
  }

  addCategory(newCategory: string) {
    this.categories.push(newCategory);
    this.router.navigate(['/']);
  }
}
