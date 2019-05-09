import { Component, OnInit } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import { Observable } from '@firebase/util';
import { AuthService } from '../services/auth.service';
import { AngularFireModule } from 'angularfire2';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user$ = this.authService.user;


  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  // make a call to Authservice
  loginGoogle() {
    this.authService.loginGoogle().subscribe(
      success => this.router.navigate(['/']),
      error => alert(error)
    );
  }

  loginTwitter() {
    this.authService.loginTwitter().subscribe(
      success => this.router.navigate(['/']),
      error => alert(error)
    );
  }

  loginfb() {
    this.authService.loginfb().subscribe(
      success => this.router.navigate(['/']),
      error => alert(error)
    );
  }

  loginGitHub() {
    this.authService.loginGithub().subscribe(
      success => this.router.navigate(['/']),
      error => alert(error)
    );
  }

  loginEmail(email, password) {
    this.authService.loginEmail(email, password).subscribe(
      success => {
        this.authService.isAdmin().subscribe(
          data => {
            if (data === true) {
              this.authService.logout();
              alert('Invalid username or password');
            } else { this.router.navigate(['/']); }
          });
      },
      error => alert(error + ' If you have not registered yet, you can do it under the register tab.'),
    );
  }

  resetPassword(email) {
    this.authService.resetPassword(email);
  }
}
