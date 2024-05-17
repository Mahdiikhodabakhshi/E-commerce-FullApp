import { Component } from '@angular/core';
import {Content} from "../../../interfaces/HomePageable";
import {AdminService} from "../../../services/admin.service";
import {registerLocaleData} from "@angular/common";
import localeES from "@angular/common/locales/es";
import {faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {faPenToSquare} from "@fortawesome/free-solid-svg-icons/faPenToSquare";
import {FormValidator} from "../../validator/formValidator";

registerLocaleData(localeES);

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {

  homeList !:Content[];

  page = 0;
  size = 5;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;

  edit: boolean = false;



  houseTypeFilter?: string;
  minPriceFilter?: number;
  maxPriceFilter?: number;
  locationFilter?: string;

  homeForm: FormGroup = this.formBuilder.group({
    id:[''],
    title: ['', [Validators.required , FormValidator.noWhite]],
    description: ['', [Validators.required , FormValidator.noWhite]],
    location: ['', [Validators.required , FormValidator.noWhite]],
    price: [0, [Validators.required , Validators.min(0) ]],
    homeType: ['', [Validators.required ]],
    uploadDate: [new Date()],
    image:[''],
    imageType:['']
  });

  get id():any{
    return this.homeForm.get('id')?.value;
  }

  get title():any{
    return this.homeForm.get('title');
  }
  get description():any{
    return this.homeForm.get('description');
  }
  get location():any{
    return this.homeForm.get('location');
  }
  get price():any{
    return this.homeForm.get('price');
  }

  get homeType():any{
    return this.homeForm.get('homeType');
  }

  get uploadDate():any{
    return this.homeForm.get('uploadDate');
  }

  get imageType():any{
    return this.homeForm.get('imageType')?.value
  }

  get image():any{
    return this.homeForm.get('image')?.value
  }



  constructor(private adminService:AdminService , private formBuilder: FormBuilder) {
    this.loadHomes();
  }


  private loadHomes() {

    const filter:string|undefined = this.buildFilters();

    this.adminService.getHomes(this.page , this.size , filter).subscribe({
      next:data => {
        console.log(data);
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

  toPreviousPage() {
    this.page -=1;
    this.loadHomes();
  }

  toNextPage() {
    this.page += 1;
    this.loadHomes();

  }

  newItem() {
    this.edit = false;
    this.homeForm.reset();
    this.homeForm.get('uploadDate')?.setValue(new Date())

  }

  loadItem(home: Content) {
    this.homeForm.setValue(home);
    this.edit = true;

  }

  protected readonly faTrashCan = faTrashCan;


  onSubmit() {
    if (this.edit){
      this.adminService.updateHome(this.homeForm.getRawValue()).subscribe({
        next:value => {
          this.loadHomes()
        },
        error:err => console.log(err),
        complete:()=>console.log('home updated')
      })
    }else {
      this.adminService.addHome(this.homeForm.getRawValue()).subscribe({
        next:value => {
          this.loadHomes();
        },
        error:err => console.log(err),
        complete:()=>console.log('new home created')
      })
    }

  }

  includeImageInItem($event: Event) {

    const inputFile = $event.target as HTMLInputElement;
    const file: File | null = inputFile.files?.item(0) ?? null;

    this.readFileAsString(file!).then(
      (result) =>{
          const imageType:string = this.getImageType(result);
          console.log(imageType);
        const imageBase64: string = this.getImageBase64(result);
        console.log(imageBase64);
        this.homeForm.get('imageType')?.setValue(imageType);
        this.homeForm.get('image')?.setValue(imageBase64);

      },
      (error)=>console.log(error)
    )


  }


  private readFileAsString(file: File) {
    return new Promise<string>(function(resolve, reject) {
      let reader: FileReader = new FileReader();
      reader.readAsDataURL(file)
      reader.onload = function() {
        resolve(this.result as string)
      }
    })
  }

  private getImageType(imageString: string): string {
    const imageStringParts: string[] = imageString.split(",");
    if (imageStringParts.length == 2) {
      return imageStringParts[0];
    } else {
      return "";
    }
  }

  private getImageBase64(imageString: string): string {
    const imageStringParts: string[] = imageString.split(",");
    if (imageStringParts.length == 2) {
      return imageStringParts[1];
    } else {
      return "";
    }
  }


  protected readonly faPenToSquare = faPenToSquare;

  removeHome(id: number) {
    if (confirm('Do you confirm delete home?')){
      this.adminService.deleteHome(id).subscribe({
        next:()=>{
          alert('home deleted!');
          this.loadHomes();
        },
        error:err => console.log(err),
        complete:()=>console.log('home deleted !!!!')
      })
    }
  }
}
