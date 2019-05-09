# Group6Shop

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.7.4.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Deploying to Firebase
- Run `npm install -g firebase-tools`
- Run `firebase login`
- From the project root directory, `run ng build --prod`
- From same directory, run `firebase deploy`

After first deployment, step 3 and 4 should suffice.

- From root directory, run `firebase init`
- Choose hosting
- Set dist as public directory
- Yes on configure as single-page app
- Do not overwrite current index.html

## ALL cmd that needs for this project 
- npm install --save
- npm install -g @angular/cli@latest
- npm install --save firebase
- npm install firebase angularfire2 --save
- npm install --save @angular/material @angular/cdk
- npm install --save @angular/animations
- npm install --save @ng-bootstrap/ng-bootstrap
- rd .git /S/Q (not important)
- rd .e2e /s /q (not important)
- rd .e2e /s /q (not important)