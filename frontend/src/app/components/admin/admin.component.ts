import { Component } from '@angular/core';
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

constructor(private router:Router , private loginService:LoginService) {
}
  protected readonly faArrowLeft = faArrowLeft;

  logout() {
    this.loginService.logout();
    this.router.navigate(['/initial']);

  }
}
