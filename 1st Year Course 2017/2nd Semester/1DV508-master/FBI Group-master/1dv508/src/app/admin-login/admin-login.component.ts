import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { tap, map, take } from 'rxjs/operators';
// import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  constructor(public auth: AuthService, private router: Router) { }

  hide = true;

  ngOnInit() { }

  loginAdmin(email, password) {
    this.auth.loginEmail(email, password).subscribe(
      success => {
        this.auth.user$.pipe(
          take(1),
          map(user => user && user.roles.admin ? true : false),
          tap(isAdmin => {
            console.log(isAdmin);
            if (!isAdmin) {
              this.auth.logout();
            }
          })
        );
        this.router.navigate(['/']);
      },
      error => alert(error + 'Better luck next time!')
    );
  }
}
