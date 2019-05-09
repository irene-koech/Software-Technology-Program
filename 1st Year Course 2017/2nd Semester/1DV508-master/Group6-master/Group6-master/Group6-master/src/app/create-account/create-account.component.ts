import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  hide = true;
  constructor(public AuthService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  registerEmail(email: string, password: string){
    this.AuthService.emailSignUp(email, password).subscribe(
      success => this.router.navigate(['/']),
      error => alert(error)
    );
  }
}
