import { Component } from '@angular/core';
import {UserService} from "../../../services/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";
import {FormValidator} from "../../validator/formValidator";

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent {

  contactUsForm : FormGroup = this.formBuilder.group({
    subject: ['', [Validators.required , FormValidator.noWhite]],
    email: ['', [Validators.required, Validators.email , FormValidator.noWhite]],
    message: ['', Validators.required],
  });


  constructor(private userService: UserService , private formBuilder: FormBuilder) {
  }

  get email():any{
    return this.contactUsForm.controls['email'];
  }
  get message():any{
    return this.contactUsForm.controls['message'];

  }
  get subject():any{
    return this.contactUsForm.controls['subject'];
  }

  onSubmit() {

    this.userService.sendContactUs(this.contactUsForm.getRawValue()).subscribe({
      next: (result) => {
        alert('thanks for your message');
        this.contactUsForm.reset();

      },
      error: (error) => {console.log(error)},
      complete:()=>console.log('message sent'),
    })

  }

  protected readonly faArrowLeft = faArrowLeft;
}
