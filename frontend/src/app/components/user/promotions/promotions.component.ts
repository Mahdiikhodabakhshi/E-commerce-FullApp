import { Component } from '@angular/core';
import {PromotionContent} from "../../../interfaces/PromotionInterface";
import {UserService} from "../../../services/user.service";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent {
  promotions !: PromotionContent[];

  page = 0;
  size = 8;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;


  constructor(private userService : UserService) {
    this.loadPromotions();
  }

  private loadPromotions() {
    this.userService.getPromotions(this.page,this.size).subscribe({
      next:data => {
        this.promotions = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error:err => console.log(err),
      complete:()=>console.log('promotion loaded')
    })

  }

  protected readonly faArrowLeft = faArrowLeft;

  toPreviousPage() {
    this.page -=1;
    this.loadPromotions();

  }

  toNextPage() {
    this.page += 1;
    this.loadPromotions();

  }
}
