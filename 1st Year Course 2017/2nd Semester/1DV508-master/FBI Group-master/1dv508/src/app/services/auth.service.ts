import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import { firebase } from '@firebase/app';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { AngularFirestore, AngularFirestoreDocument } from 'angularfire2/firestore';
import { switchMap } from 'rxjs/operators';
import { User } from '../user';

@Injectable()
export class AuthService {
  user$: Observable<User>;

  constructor(private afAuth: AngularFireAuth,
    private afs: AngularFirestore,
    private router: Router) {
    //// Get auth data, then get firestore user document || null
    this.user$ = this.afAuth.authState.switchMap(user => {
      if (user) {
        return this.afs.doc<User>(`Users/${user.uid}`).valueChanges();
      } else {
        return Observable.of(null);
      }
    });
  }

  private oAuthLogin(provider) {
    return this.afAuth.auth.signInWithPopup(provider)
      .then((credential) => {
        this.updateUserData(credential.user);
      });
  }

  private updateUserData(user) {
    // Sets user data to firestore on login
    const userRef: AngularFirestoreDocument<any> = this.afs.doc(`Users/${user.uid}`);
    const data: User = {
      uid: user.uid,
      email: user.email,
      roles: {
        customer: true,
      },
      address: {},
    };
    return userRef.set(data, { merge: true });
  }

  isAdmin(user: User): boolean {
    if (!user) { return false; }
    if (user.roles['admin']) { return true; }
    return false;
  }

  isAuthenticated(user: User): boolean {
    if (user) { return true; }
    return false;
  }

  loginEmail(email, password) {
    return Observable.fromPromise(
      this.afAuth.auth.signInWithEmailAndPassword(email, password)
        .then((credential) => {
          this.updateUserData(credential);
        })
    );
  }

  loginfb() {
    const provider = new firebase.auth.FacebookAuthProvider();
    return Observable.fromPromise(this.oAuthLogin(provider));
  }

  loginGoogle() {
    const provider = new firebase.auth.GoogleAuthProvider();
    return Observable.fromPromise(this.oAuthLogin(provider));
  }

  loginTwitter() {
    const provider = new firebase.auth.TwitterAuthProvider();
    return Observable.fromPromise(this.oAuthLogin(provider));
  }

  loginGithub() {
    const provider = new firebase.auth.GithubAuthProvider();
    return Observable.fromPromise(this.oAuthLogin(provider));
  }

  emailSignUp(email: string, password: string): Observable<any> {
    return Observable.fromPromise(
      this.afAuth.auth.createUserWithEmailAndPassword(email, password)
    );
  }

  logout() {
    this.afAuth.auth.signOut();
    this.router.navigate([this.router.url]);
  }

  resetPassword(email) {
    return this.afAuth.auth.sendPasswordResetEmail(email)
      .then(() => alert('A password reset email has been sent to your email adress.'))
      .catch((error) => alert(error));
  }
}
