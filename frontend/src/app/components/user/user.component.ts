import { Component } from '@angular/core';
import {UserService} from "../../services/user.service";
import {ActivityContent} from "../../interfaces/ActivityPageable";
import {PromotionContent} from "../../interfaces/PromotionInterface";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  newActivities !:ActivityContent[]
  newPromotions !:PromotionContent[]

  page = 0;
  size = 3;

  constructor(private userService:UserService) {
    this.loadActivities();
    this.loadPromotions();
  }

  private loadActivities() {
    this.userService.getNewActivities(this.page,this.size).subscribe({
      next:value => this.newActivities = value.content,
      error:err => console.log(err),
      complete:()=>console.log('loaded new activities')
    })
  }

  private loadPromotions() {
    this.userService.getNewPromotions(this.page,this.size).subscribe({
      next:value => this.newPromotions = value.content,
      error:err => console.log(err),
      complete:()=>console.log('promotion loaded')
    })
  }
}
