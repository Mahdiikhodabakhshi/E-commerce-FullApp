import { Component } from '@angular/core';
import {ActivityContent} from "../../../interfaces/ActivityPageable";
import {UserService} from "../../../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";

@Component({
  selector: 'app-activity-details',
  templateUrl: './activity-details.component.html',
  styleUrls: ['./activity-details.component.css']
})
export class ActivityDetailsComponent {

  activityDetails !: ActivityContent;

  constructor(private userService: UserService , private router:ActivatedRoute) {
    this.loadActivity();
  }

  private loadActivity() {
    const id = this.router.snapshot.params['id'];
    this.userService.getOneActivity(id).subscribe({
      next:value => {
        this.activityDetails = value;
      },
      error:err => console.log(err),
      complete:()=>console.log('activity details loaded')
    })
  }

  protected readonly alert = alert;
  protected readonly faArrowLeft = faArrowLeft;
}
