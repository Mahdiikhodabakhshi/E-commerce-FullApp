import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderInicialComponent } from './common/header-inicial/header-inicial.component';
import { FooterInicialComponent } from './common/footer-inicial/footer-inicial.component';
import { InicialPageComponent } from './components/inicial-page/inicial-page.component';
import { NotFoundedComponent } from './components/not-founded/not-founded.component';
import { AuthComponent } from './components/auth/auth.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptorInterceptor} from "./interceptor/token-interceptor.interceptor";


@NgModule({
    declarations: [
        AppComponent,
        HeaderInicialComponent,
        FooterInicialComponent,
        InicialPageComponent,
        NotFoundedComponent,
        AuthComponent,


    ],
    imports: [

        BrowserModule,
        AppRoutingModule,
        FontAwesomeModule,
        ReactiveFormsModule,
        HttpClientModule,
    ],
    providers: [
      {
        provide:HTTP_INTERCEPTORS,
        useClass:TokenInterceptorInterceptor,
        multi:true
      }

    ],
    exports: [

    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
