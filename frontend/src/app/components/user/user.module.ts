import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { MainComponent } from './main/main.component';
import { MenuLateralComponent } from './menu-lateral/menu-lateral.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import { HomeSearchComponent } from './home-search/home-search.component';
import { HomeInfoComponent } from './home-info/home-info.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { PromotionDetailsComponent } from './promotion-details/promotion-details.component';
import { PromotionsComponent } from './promotions/promotions.component';
import { ActivitiesComponent } from './activities/activities.component';
import { ActivityDetailsComponent } from './activity-details/activity-details.component';
import { ContactUsComponent } from './contact-us/contact-us.component';


@NgModule({
  declarations: [
    UserComponent,
    MainComponent,
    MenuLateralComponent,
    HomeSearchComponent,
    HomeInfoComponent,
    PromotionDetailsComponent,
    PromotionsComponent,
    ActivitiesComponent,
    ActivityDetailsComponent,
    ContactUsComponent
  ],
    imports: [

        CommonModule,
        UserRoutingModule,

        FontAwesomeModule,
        FormsModule,
        ReactiveFormsModule,

    ]
})
export class UserModule { }
