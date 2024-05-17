import { Component } from '@angular/core';
import {UserService} from "../../../services/user.service";
import {Content} from "../../../interfaces/HomePageable";
import {registerLocaleData} from "@angular/common";
import localeES from "@angular/common/locales/es";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";

registerLocaleData(localeES);


@Component({
  selector: 'app-home-search',
  templateUrl: './home-search.component.html',
  styleUrls: ['./home-search.component.css']
})
export class HomeSearchComponent {
  homeList !:Content[];

  page = 0;
  size = 5;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;



  houseTypeFilter?: string;
  minPriceFilter?: number;
  maxPriceFilter?: number;
  locationFilter?: string;

  constructor(private userService:UserService) {
    this.loadHomes();
  }


  private loadHomes() {

    const filter:string|undefined = this.buildFilters();

    this.userService.getHomes(this.page , this.size , filter).subscribe({
      next:data => {
        this.homeList = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error:err => console.log(err),
      complete:()=> console.log('homelist loaded')
    })
  }

  private buildFilters():string|undefined {
    const filters:string[] = [];

    if (this.houseTypeFilter){
      filters.push("homeType:MATCH:"+this.houseTypeFilter);
    }
    if (this.minPriceFilter){
      filters.push("price:GREATER_THAN_EQUAL:"+this.minPriceFilter);

    }
    if (this.maxPriceFilter){
      filters.push("price:LESS_THAN_EQUAL:"+this.maxPriceFilter);
    }
    if (this.locationFilter){
      filters.push("location:MATCH:"+this.locationFilter);
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
    this.minPriceFilter = undefined;
    this.maxPriceFilter = undefined;
    this.locationFilter = undefined;
    this.houseTypeFilter = undefined;

  }

  searchByFilters() {
    this.loadHomes();

  }

  protected readonly faArrowLeft = faArrowLeft;

  toPreviousPage() {
    this.page -= 1;
    this.loadHomes();

  }

  toNextPage() {
    this.page += 1;
    this.loadHomes();
  }
}
