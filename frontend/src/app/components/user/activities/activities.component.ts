import {ActivityContent} from "../../../interfaces/ActivityPageable";
import {Component} from "@angular/core";
import {UserService} from "../../../services/user.service";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent {

  activities !: ActivityContent[];

  page = 0;
  size = 8;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;


  constructor(private userService: UserService) {
    this.loadActivities();
  }

  private loadActivities() {
    this.userService.getActivities(this.page,this.size).subscribe({
      next:data => {
        this.activities = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error:err => console.log(err),
      complete:()=>console.log('loaded activities')
    })
  }



  toPreviousPage() {
    this.page -=1;
    this.loadActivities();

  }

  toNextPage() {
    this.page += 1;
    this.loadActivities();

  }


  protected readonly faArrowLeft = faArrowLeft;
}
