import { Injectable, Component } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class EmailService {

  constructor(private http: Http) { }

  sendEmail(userMail) {

    const url = `https://us-central1-groupsiiix.cloudfunctions.net/httpEmail`;
    const params: URLSearchParams = new URLSearchParams();
    const _options = { headers: new Headers({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }) };

    params.set('to', userMail);
    params.set('from', 'you@yoursupercoolapp.com');
    params.set('subject', 'test-email');
    params.set('content', 'Hello World');

    return this.http.post(url, params, _options)
      .toPromise()
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });

  }

}
