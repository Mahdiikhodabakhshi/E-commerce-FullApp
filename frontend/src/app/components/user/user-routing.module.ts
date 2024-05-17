import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import {HomeSearchComponent} from "./home-search/home-search.component";
import {HomeInfoComponent} from "./home-info/home-info.component";
import {PromotionsComponent} from "./promotions/promotions.component";
import {PromotionDetailsComponent} from "./promotion-details/promotion-details.component";
import {ActivitiesComponent} from "./activities/activities.component";
import {ActivityDetailsComponent} from "./activity-details/activity-details.component";
import {ContactUsComponent} from "./contact-us/contact-us.component";


const routes: Routes = [
  { path: '', component: UserComponent },
  {path:'home-search' , component:HomeSearchComponent},
  {
    path:'home-search/:id' , component:HomeInfoComponent
  },
  {
    path:'promotions' , component:PromotionsComponent
  },
  {
    path:'promotions/:id' , component:PromotionDetailsComponent
  },
  {
    path:'activities' , component:ActivitiesComponent
  },
  {
    path:'activities/:id' , component:ActivityDetailsComponent
  },
  {
    path:'contactUs',component:ContactUsComponent
  }



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
