import { Component } from '@angular/core';
import {PromotionContent} from "../../../interfaces/PromotionInterface";
import {UserService} from "../../../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";

@Component({
  selector: 'app-promotion-details',
  templateUrl: './promotion-details.component.html',
  styleUrls: ['./promotion-details.component.css']
})
export class PromotionDetailsComponent {

  promotionDetails !: PromotionContent;

  constructor(private userService : UserService , private router:ActivatedRoute)  {
    this.loadPromotion();
  }

  private loadPromotion() {
    const id = this.router.snapshot.params['id'];

    this.userService.getOnePromotion(id).subscribe({
      next:value => {
        console.log(value);
        this.promotionDetails = value
      },
      error:err => console.log(err),
      complete:()=>console.log('promotion details loaded')
    })
  }

  protected readonly alert = alert;
  protected readonly faArrowLeft = faArrowLeft;
}
