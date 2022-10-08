import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
   /* --------- Properties --------- */
  loginForm:FormGroup; //form declared
  submitted:boolean = false;

  /* --------- Constructor --------- */
  constructor(private builder:FormBuilder,
              private userService:UserService,
              private loginService:LoginService,
              private router:Router) { }

  /* --------- Methods --------- */
  /* *******************************************
   * Method Name: ngOnInit()
   * Access Type: public
   * Input Parameters: none
   * Return Type: void
   * Purpose: Build the login form.
   *          ngOnInit() is invoked only once when the directive is instantiated.
   * ******************************************* */
  ngOnInit(): void {
    /* Property: 'loginForm'
       Type: FormGroup
       Passed into FormGroup two FormControl objects - username and password.
  */
    this.loginForm = this.builder.group(
      {
        username: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]]
      }
    )
  }

  /* *******************************************
   * Method Name: getFormControls()
   * Access Type: public
   * Input Parameters: none
   * Return Type: none
   * Purpose: Getter method to get all of the form controls
   * ******************************************* */
    get getFormControls(){
      return this.loginForm.controls;
    }

  /* *******************************************
   * Method Name: onSubmit()
   * Access Type: public
   * Input Parameters: none
   * Return Type: void
   * Purpose:
   * ******************************************* */
    onSubmit(){
      this.submitted = true;
      if(this.loginForm.invalid)
        return; //don't allow the user to submit the form
      else{
        var result = this.loginService.login(this.loginForm.controls['username'].value,
                   this.loginForm.controls['password'].value);
        console.log(this.loginForm.value); // prints form values in json format
        //compare the username and password to the User[] array
        alert("User successfully logged in...");
        this.router.navigate(['/home']);
      }
    }

  /* *******************************************
   * Method Name: login()
   * Access Type: public
   * Input Parameters: none
   * Return Type: void
   * Purpose:
   * ******************************************* */
  login(){
      console.log(this.loginForm.value); // prints form values in json format
      //compare the username and password to the User[] array
  }

  }
