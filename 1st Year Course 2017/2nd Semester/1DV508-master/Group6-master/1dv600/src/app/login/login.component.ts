import { Component, OnInit } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import { Observable } from '@firebase/util';
import { AuthService } from '../services/auth.service';
import { AngularFireModule } from 'angularfire2';
import { Router } from '@angular/router';
import { tap, map, take } from 'rxjs/operators';
import { MatDialog } from '@angular/material';
import { CreateAccountComponent } from '../create-account/create-account.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;
  public user;

  constructor(
    public auth: AuthService, 
    private router: Router,
    private popup: MatDialog
  ) { }

  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);
  }

  redirect() {
    this.auth.user$.subscribe(
      user => {
        if (user.roles['admin']) {
          this.auth.logout();
        }
      }
    );
    this.router.navigate(['/']);
  }

  // make a call to Authservice
  loginGoogle() {
    this.auth.loginGoogle().subscribe(
      success => this.redirect(),
      error => alert(error)
    );
  }

  loginTwitter() {
    this.auth.loginTwitter().subscribe(
      success => this.redirect(),
      error => alert(error)
    );
  }

  loginfb() {
    this.auth.loginfb().subscribe(
      success => this.redirect(),
      error => alert(error)
    );
  }

  loginGitHub() {
    this.auth.loginGithub().subscribe(
      success => this.redirect(),
      error => alert(error)
    );
  }

  loginEmail(email, password) {
    this.auth.loginEmail(email, password).subscribe(
      success => this.redirect(),
      error => alert(error + ' If you have not registered yet, you can do it under the register tab.'),
    );
  }

  resetPassword(email) {
    this.auth.resetPassword(email);
  }

  registerPopup(){
    const dialogRef = this.popup.open(CreateAccountComponent, {
      height: '50%', width: '60%'
    })
  }
}
