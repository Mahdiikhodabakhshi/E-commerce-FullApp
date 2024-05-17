import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActivityContent, ActivityPageable} from "../interfaces/ActivityPageable";
import {HomePageable , Content} from "../interfaces/HomePageable";
import {ContactUsInterface} from "../interfaces/ContactUsInterface";
import {PromotionContent, PromotionInterface} from "../interfaces/PromotionInterface";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  URL = 'http://localhost:8080/api';
  constructor(private http:HttpClient) { }

  getNewActivities(page:number , size:number ):Observable<ActivityPageable>{
    let newUrl = this.URL+ "/activities/new-activities?page="+page+"&size="+size

    return this.http.get<ActivityPageable>(newUrl)
  }

  getNewPromotions(page:number , size:number):Observable<PromotionInterface>{
    let newUrl = this.URL+ "/promotions/new-promotions?page="+page+"&size="+size

    return this.http.get<PromotionInterface>(newUrl)
  }


  getHomes(page:number,size:number,filter?:string):Observable<HomePageable>{
    let newUrl = this.URL  +'/homes/new-homes?page='+page+"&size="+size;
    if (filter){
      newUrl = newUrl + "&filter="+filter
    }
    return this.http.get<HomePageable>(newUrl)
  }

  getOneHome(id:number):Observable<Content>{
    return this.http.get<Content>(this.URL+'/homes/'+id);

  }

  sendContactUs(ContactUs:ContactUsInterface):Observable<ContactUsInterface>{
    return this.http.post<ContactUsInterface>(this.URL+'/contacts',ContactUs);
  }

  getPromotions(page:number,size:number):Observable<PromotionInterface>{
    let newUrl = this.URL  +'/promotions?page='+page+"&size="+size;

    return this.http.get<PromotionInterface>(newUrl)
  }

  getOnePromotion(id:number):Observable<PromotionContent>{
    return this.http.get<PromotionContent>(this.URL+'/promotions/'+id);
  }

  getActivities(page:number,size:number):Observable<ActivityPageable>{
    let newUrl = this.URL  +'/activities?page='+page+"&size="+size;
    return this.http.get<ActivityPageable>(newUrl)
  }

  getOneActivity(id:number):Observable<ActivityContent>{
    return this.http.get<ActivityContent>(this.URL+'/activities/'+id);
  }






}
