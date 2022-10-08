import { Component } from '@angular/core';
import { TitleStrategy } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './services/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [
    ],
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  //isUserLoggedIn!:Observable<boolean>;
  isUserLoggedIn:boolean;

  constructor(private loginService:LoginService){

  }

  ngOnInit(){
    this.isUserLoggedIn = this.loginService._isUserLoggedIn;
  }


}
