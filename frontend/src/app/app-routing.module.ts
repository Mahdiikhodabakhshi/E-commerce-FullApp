import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InicialPageComponent} from "./components/inicial-page/inicial-page.component";
import {NotFoundedComponent} from "./components/not-founded/not-founded.component";
import {AuthComponent} from "./components/auth/auth.component";
import {UserRoleGuard} from "./guards/user-role.guard";
import {AdminRoleGuard} from "./guards/admin-role.guard";

const routes: Routes = [
  {path:'' , redirectTo:'/initial' , pathMatch:"full"},
  {path:'initial', component:InicialPageComponent},
  {
    path:'login' , component: AuthComponent
  },
  { path: 'user',
    loadChildren: () => import('./components/user/user.module').then(m => m.UserModule) ,
    canActivate:[UserRoleGuard] },
  { path: 'admin', loadChildren: () => import('./components/admin/admin.module').then(m => m.AdminModule) , canActivate:[AdminRoleGuard] },
  {
    path:'**', component: NotFoundedComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
