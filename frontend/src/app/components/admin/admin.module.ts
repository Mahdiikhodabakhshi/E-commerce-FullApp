import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminActivityComponent } from './admin-activity/admin-activity.component';
import { AdminPromotionComponent } from './admin-promotion/admin-promotion.component';
import { AdminContactUsComponent } from './admin-contact-us/admin-contact-us.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";


@NgModule({
  declarations: [
    AdminComponent,
    AdminHomeComponent,
    AdminActivityComponent,
    AdminPromotionComponent,
    AdminContactUsComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    FontAwesomeModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
