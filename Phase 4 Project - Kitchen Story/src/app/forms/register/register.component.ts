import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  //Properties
  registerForm:FormGroup; //form declared
  submitted:boolean = false;
  newUser = new User();
  isLoggedIn:boolean = false;

  //Constructor
  constructor(private builder:FormBuilder,
              private userService:UserService,
              private router:Router) { }

  //Methods
  ngOnInit(): void {
    this.registerForm = this.builder.group(
      {
        firstName: ['', Validators.required],
        lastName:['', Validators.required],
        email:['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        admin: ['']
      }
    )
  }

  //method to get all of the form controls
  get form(){
    return this.registerForm.controls;
  }

  onSubmit(){
    this.submitted = true;
    if(this.registerForm.invalid)
      return; //don't allow the user to submit the form
    else{
      alert("Form submitted for approval...")
      this.newUser.name = this.registerForm.get('firstName')?.value + " "
                          + this.registerForm.get('lastName')?.value;
      this.newUser.userId = this.refineUserData(this.newUser);
      this.userService.addUser(this.newUser);
      this.isLoggedIn = true;
      console.log(this.newUser);
      this.userService.setUser(this.newUser);
      this.router.navigate(['/home']);
    }
  }

  refineUserData(u:User){
    u.userId = this.userService.userCount;
    return u.userId;
  }

  onChange($event:any){
    return this.newUser.isAdmin ? false : true;
  }

}
