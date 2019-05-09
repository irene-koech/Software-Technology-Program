import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
// import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  hide = true;
  // email = new FormControl('', [Validators.required, Validators.email]);

  // getErrorMessage() {
  //   return this.email.hasError('required') ? 'You must enter a value' :
  //       this.email.hasError('email') ? 'Not a valid email' :
  //           '';
  // }

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  loginAdmin(email, password) {
    this.auth.loginEmail(email, password).subscribe(
      success => {if (this.auth.isAdmin) {
        // this.auth.admin = true;
        // console.log(this.auth.admin);
        this.router.navigate(['/']);
       }},
      error => alert(error + 'Better luck next time!')
    );
  }
}
