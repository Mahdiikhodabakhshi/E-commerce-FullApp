import { Component } from '@angular/core';
import {AdminService} from "../../../services/admin.service";
import {ContactUsContent} from "../../../interfaces/ContactUsPageable";
import {faTrashCan} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-admin-contact-us',
  templateUrl: './admin-contact-us.component.html',
  styleUrls: ['./admin-contact-us.component.css']
})
export class AdminContactUsComponent {

  contactUsList !:ContactUsContent[];

  page = 0;
  size = 5;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;




  subjectFilter?: string;
  messageFilter?: string;



  constructor(private adminService:AdminService) {
    this.loadContactUs();
  }


  private loadContactUs() {

    const filter:string|undefined = this.buildFilters();

    this.adminService.getContactUs(this.page , this.size , filter).subscribe({
      next:data => {
        console.log(data);
        this.contactUsList = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error:err => console.log(err),
      complete:()=> console.log('contactUsList loaded')
    })
  }

  private buildFilters():string|undefined {
    const filters:string[] = [];

    if (this.subjectFilter){
      filters.push("subject:MATCH:"+this.subjectFilter);
    }
    if (this.messageFilter){
      filters.push("message:MATCH:"+this.messageFilter);
    }
    if(filters.length > 0){
      let globalFilters:string = "";
      for (let filter of filters){
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0, globalFilters.length-1);
      return globalFilters;
    }else return undefined;


  }

  cleanSearch() {
    this.subjectFilter = undefined;
    this.messageFilter = undefined;
  }

  searchByFilters() {
    this.loadContactUs();

  }

  toPreviousPage() {
    this.page -=1;
    this.loadContactUs();
  }

  toNextPage() {
    this.page += 1;
    this.loadContactUs();

  }







  removeContactUs(id: number) {
    if (confirm('Do you confirm delete contact message?')){
      this.adminService.deleteContactUs(id).subscribe({
        next:()=>{
          alert('contact message deleted!');
          this.loadContactUs();
        },
        error:err => console.log(err),
        complete:()=>console.log('contact message deleted !!!!')
      })
    }
  }


  protected readonly faTrashCan = faTrashCan;
}
