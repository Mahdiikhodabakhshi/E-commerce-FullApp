import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Content, HomePageable} from "../interfaces/HomePageable";
import {PromotionContent, PromotionInterface} from "../interfaces/PromotionInterface";
import {ActivityContent, ActivityPageable} from "../interfaces/ActivityPageable";
import {ContactUsContent, ContactUsPageable} from "../interfaces/ContactUsPageable";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }


  // ---------------- CONTACTUS SECTION ------------------------

  getContactUs(page:number,size:number ,filter?:string):Observable<ContactUsPageable>{
    let newUrl = this.URL  +'/contacts?page='+page+"&size="+size;
    if (filter){
      newUrl = newUrl + "&filter="+filter
    }
    return this.http.get<ContactUsPageable>(newUrl)
  }

  deleteContactUs(id:number):Observable<any>{
    return this.http.delete(this.URL+'/contacts/'+id)
  }



  // ---------------- PROMOTION SECTION ------------------------

  getPromotions(page:number,size:number,filter?:string):Observable<PromotionInterface>{
    let newUrl = this.URL  +'/promotions?page='+page+"&size="+size;
    if (filter){
      newUrl = newUrl + "&filter="+filter
    }

    return this.http.get<PromotionInterface>(newUrl)
  }
  addPromotion(promotion:PromotionContent):Observable<PromotionContent>{
    return this.http.post<PromotionContent>(this.URL+'/promotions',promotion)
  }
  updatePromotion(promotion:PromotionContent):Observable<PromotionContent>{
    return this.http.patch<PromotionContent>(this.URL+'/promotions',promotion)
  }

  deletePromotion(id:number):Observable<any>{
    return this.http.delete(this.URL+'/promotions/'+id)
  }


  // ---------------- ACTIVITY SECTION ------------------------

  getActivities(page:number,size:number , filter?:string):Observable<ActivityPageable>{
    let newUrl = this.URL  +'/activities?page='+page+"&size="+size;
    if (filter){
      newUrl = newUrl + "&filter="+filter
    }
    return this.http.get<ActivityPageable>(newUrl)
  }

  addActivity(activity:ActivityContent):Observable<ActivityContent>{
    return this.http.post<ActivityContent>(this.URL+'/activities',activity)
  }
  updateActivity(activity:ActivityContent):Observable<ActivityContent>{
    return this.http.patch<ActivityContent>(this.URL+'/activities',activity)
  }

  deleteActivity(id:number):Observable<any>{
    return this.http.delete(this.URL+'/activities/'+id)
  }

  // ---------------- HOME SECTION ------------------------

  getHomes(page:number,size:number,filter?:string):Observable<HomePageable>{
    let newUrl = this.URL  +'/homes/new-homes?page='+page+"&size="+size;
    if (filter){
      newUrl = newUrl + "&filter="+filter
    }
    return this.http.get<HomePageable>(newUrl)
  }


  addHome(home:Content):Observable<Content>{
    return this.http.post<Content>(this.URL+'/homes',home)
  }
  updateHome(home:Content):Observable<Content>{
    return this.http.patch<Content>(this.URL+'/homes',home)
  }

  deleteHome(id:number):Observable<any>{
    return this.http.delete(this.URL+'/homes/'+id);
  }



}
