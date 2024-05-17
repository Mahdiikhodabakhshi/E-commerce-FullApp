import {Component, Input} from '@angular/core';
import {
  faCircleInfo,
  faCompass,
  faHouse,
  faPercent,
  faPersonCircleQuestion,
  faRightFromBracket
} from "@fortawesome/free-solid-svg-icons";
import {LoginService} from "../../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu-lateral',
  templateUrl: './menu-lateral.component.html',
  styleUrls: ['./menu-lateral.component.css']
})
export class MenuLateralComponent {
  @Input() activeOp?:boolean = false ;
  @Input() promotionActive?:boolean = false;
  @Input() activityActive ?: boolean = false;
  constructor(private loginService: LoginService , private router: Router)  {
  }

  protected readonly faPersonCircleQuestion = faPersonCircleQuestion;
  protected readonly faCircleInfo = faCircleInfo;
  protected readonly faPercent = faPercent;
  protected readonly faCompass = faCompass;
  protected readonly faHouse = faHouse;
  protected readonly faRightFromBracket = faRightFromBracket;

  logout() {
    this.loginService.logout();
    console.log(localStorage.getItem("auth_token") , 'from menu lateral');
    this.router.navigate(['/initial']);

  }
}
