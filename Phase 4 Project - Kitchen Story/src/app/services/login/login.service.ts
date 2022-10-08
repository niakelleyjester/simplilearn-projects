import { Injectable, Input } from '@angular/core';
/* We mark our Login service class as available for dependency injection by decorating it with
   the @Injectable() annotation. */
@Injectable({
  providedIn: 'root'
})

/* Note: Whenever we create a new service class and want to use it, we should specify it in the providers
array of our module class to state that we want to use this service in that module (i.e. AppModule). */
export class LoginService {

  /* --------- Properties --------- */
  _isUserLoggedIn:boolean = false;

  /* --------- Constructor --------- */
  constructor() { }

  /* --------- Methods --------- */

  @Input()
  get isUserLoggedIn(){
    return this._isUserLoggedIn;
  }

  /* *******************************************
   * Method Name: login()
   * Access Type: public
   * Input Parameters: Username String, Password String
   * Return Type: boolean
   * Purpose: To validate username and password against the application's database, upon submitting the form to the server.
   *          In a real application, our login method should call an authentication API on a server with the credentials.
   *          To simplify our illustration for now, we authenticate with hardcoded values.
   * ******************************************* */
  login(username: string, password: string):boolean{
    if(username === "niakelley" && password === "abc123")
      this._isUserLoggedIn = true;
    else
      this._isUserLoggedIn = false;

    return this._isUserLoggedIn;
  }



  /* *******************************************
   * Method Name: logout()
   * Access Type: public
   * Input Parameters: none
   * Return Type: boolean
   * Purpose: To reset the state of the isLoggedIn property back to false
   *          We don’t call a remote service because the server is not aware if a user is logged in or not.
   * ******************************************* */
  logout():boolean{
    this._isUserLoggedIn=false;
    return this._isUserLoggedIn;
  }
}
