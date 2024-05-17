import { Component } from '@angular/core';
import {Content} from "../../../interfaces/HomePageable";
import {UserService} from "../../../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ContactUsInterface} from "../../../interfaces/ContactUsInterface";
import {FormValidator} from "../../validator/formValidator";

@Component({
  selector: 'app-home-info',
  templateUrl: './home-info.component.html',
  styleUrls: ['./home-info.component.css']
})
export class HomeInfoComponent {

  homeInfo !:Content ;


  contactForm: FormGroup = this.formBuilder.group({

    name: ['', [Validators.required ,FormValidator.noWhite]],
    phone: ['',[ Validators.required , FormValidator.noWhite ]],
    email: ['', [Validators.required , FormValidator.noWhite, Validators.email]],
  });


  get name():any{
    return this.contactForm.get('name')
  }
  get phone():any{
    return this.contactForm.get('phone')
  }
  get email():any{
    return this.contactForm.get('email')
  }
  get message():any{
    return this.contactForm.get('message')
  }

  constructor(private userService:UserService , private route:ActivatedRoute, private formBuilder : FormBuilder) {
    this.loadHome();
  }

  private loadHome() {
    const id =this.route.snapshot.params['id'];
    this.userService.getOneHome(id).subscribe({
      next:value => this.homeInfo = value,
      error:err => console.log(err),
      complete:()=>console.log('home info loaded')
    })
  }

  onSubmit(id:number) {
    let contactUsMsg: ContactUsInterface = {
      subject: `home contact for house ${id}`,
      email: this.contactForm.controls['email'].value,
      message: `name : ${this.contactForm.get('name')?.value}, phone: ${this.contactForm.get('phone')?.value}, email: ${this.contactForm.get('email')?.value}`
    };
    console.log(contactUsMsg);

    this.userService.sendContactUs(contactUsMsg).subscribe({
      next:value => alert('we will be in contact with you as soon as possible'),
      error:err => console.log(err),
      complete:()=>console.log('contactHome sent')
    })




  }
}
