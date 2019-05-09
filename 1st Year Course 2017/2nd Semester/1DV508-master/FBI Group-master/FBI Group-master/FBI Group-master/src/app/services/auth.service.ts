import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {
  public user: Observable<firebase.User>;

  constructor(private af: AngularFireAuth, private router: Router) {
    this.user = af.authState;
   }

   loginEmail(email: string, password: string): Observable<any> {
      return Observable.fromPromise(
        this.af.auth.signInWithEmailAndPassword(email, password)
      );
   }

   // FB
   loginfb() {
    return Observable.fromPromise(
      this.af.auth.signInWithPopup(new firebase.auth.FacebookAuthProvider())
    );
  }

   // google
   loginGoogle() {
    return Observable.fromPromise(
      this.af.auth.signInWithPopup(new firebase.auth.GoogleAuthProvider())
    );
   }

   // twitter
   loginTwitter() {
    return Observable.fromPromise(this.af.auth.signInWithPopup(
      new firebase.auth.TwitterAuthProvider())
    );
  }

  loginGithub() {
    return Observable.fromPromise(
      this.af.auth.signInWithPopup(
        new firebase.auth.GithubAuthProvider())
    );
}
  emailSignUp(email: string, password: string): Observable<any> {
    return Observable.fromPromise(
      this.af.auth.createUserWithEmailAndPassword(email, password)
    );
  }

  isAuthenticated(): Observable<boolean> {
    return this.user.map(user => user && user.uid !== undefined);
  }

  isAdmin(): Observable<boolean> {
    return this.user.map(user => user && user.uid === 'za3usavGnjPgpXYqK2FYMVIHKFV2');
  }

  currentUserObservable(): any {
    return this.af.auth;
  }

  logout() {
    this.af.auth.signOut();
  }

  resetPassword(email) {
    return this.af.auth.sendPasswordResetEmail(email)
    .then(() => alert('A password reset email has been sent to your email adress.'))
    .catch((error) => alert(error));
  }
}
