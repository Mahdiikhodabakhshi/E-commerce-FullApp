import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', Validators.required]
  });
  signupForm: FormGroup = this.fb.group({
    email: ['', Validators.required],
    password: ['', [Validators.required]],
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    role: ['USER', Validators.required]

  });




  constructor(private fb: FormBuilder , private router: Router , private loginService:LoginService)  {}

  get loginFormControl() {
    return this.loginForm.controls;
  }

  get signupFormControl() {
    return this.signupForm.controls;
  }

  onLogin() {
    if (this.loginForm.valid) {
      // Handle login logic
      console.log('Login form submitted:', this.loginForm.value);
      this.loginService.login(this.loginForm.getRawValue()).subscribe({
        next:data => {
          console.log(data.token)
          this.loginService.saveToken(data.token)
          if (this.loginService.isUser()){
            this.router.navigate(["/user"])
          }
          else {
            this.router.navigate(["/admin"])

          }
        },
        error:err => console.log(err),
        complete:()=>console.log('done')
      })
    }
  }

  onSignup() {
    if (this.signupForm.valid) {
      // Handle signup logic
      console.log('Signup form submitted:', this.signupForm.value);
      this.loginService.register(this.signupForm.getRawValue()).subscribe({
        next:data=> {
          console.log(data.token)
          this.loginService.saveToken(data.token);
          this.router.navigate(["/user"])

        }
      })

    }
  }



}
