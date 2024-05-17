import { Component } from '@angular/core';
import {ActivityContent} from "../../../interfaces/ActivityPageable";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../../services/admin.service";
import {PromotionContent} from "../../../interfaces/PromotionInterface";
import {faPenToSquare} from "@fortawesome/free-solid-svg-icons/faPenToSquare";
import {faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {FormValidator} from "../../validator/formValidator";

@Component({
  selector: 'app-admin-promotion',
  templateUrl: './admin-promotion.component.html',
  styleUrls: ['./admin-promotion.component.css']
})
export class AdminPromotionComponent {

  promotionList !:PromotionContent[];

  page = 0;
  size = 5;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;

  edit: boolean = false;



  titleFilter?: string;
  locationFilter?: string;

  promotionForm: FormGroup = this.formBuilder.group({
    id:[''],
    title: ['', [Validators.required , FormValidator.noWhite]],
    description: ['', [Validators.required , FormValidator.noWhite]],
    location: ['', [Validators.required , FormValidator.noWhite]],
    uploadDate: [new Date()],
    image:[''],
    imageType:['']
  });

  get id():any{
    return this.promotionForm.get('id')?.value;
  }

  get title():any{
    return this.promotionForm.get('title');
  }
  get description():any{
    return this.promotionForm.get('description');
  }
  get location():any{
    return this.promotionForm.get('location');
  }




  get imageType():any{
    return this.promotionForm.get('imageType')?.value
  }

  get image():any{
    return this.promotionForm.get('image')?.value
  }



  constructor(private adminService:AdminService , private formBuilder: FormBuilder) {
    this.loadPromotions();
  }


  private loadPromotions() {

    const filter:string|undefined = this.buildFilters();

    this.adminService.getPromotions(this.page , this.size , filter).subscribe({
      next:data => {
        console.log(data);
        this.promotionList = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error:err => console.log(err),
      complete:()=> console.log('promotionLIST loaded')
    })
  }

  private buildFilters():string|undefined {
    const filters:string[] = [];

    if (this.titleFilter){
      filters.push("title:MATCH:"+this.titleFilter);
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
    this.titleFilter = undefined;
    this.locationFilter = undefined;
  }

  searchByFilters() {
    this.loadPromotions();

  }

  toPreviousPage() {
    this.page -=1;
    this.loadPromotions();
  }

  toNextPage() {
    this.page += 1;
    this.loadPromotions();

  }

  newItem() {
    this.edit = false;
    this.promotionForm.reset();
    this.promotionForm.get('uploadDate')?.setValue(new Date())

  }

  loadItem(promotion: PromotionContent) {
    this.promotionForm.setValue(promotion);
    this.edit = true;

  }


  onSubmit() {
    if (this.edit){
      this.adminService.updatePromotion(this.promotionForm.getRawValue()).subscribe({
        next:value => {
          this.loadPromotions()
        },
        error:err => console.log(err),
        complete:()=>console.log('promotion updated')
      })
    }else {
      this.adminService.addPromotion(this.promotionForm.getRawValue()).subscribe({
        next:value => {
          this.loadPromotions();
        },
        error:err => console.log(err),
        complete:()=>console.log('new promotion created')
      })
    }

  }

  includeImageInItem($event: Event) {

    const inputFile = $event.target as HTMLInputElement;
    const file: File | null = inputFile.files?.item(0) ?? null;

    this.readFileAsString(file!).then(
      (result) =>{
        const imageType:string = this.getImageType(result);
        const imageBase64: string = this.getImageBase64(result);
        this.promotionForm.get('imageType')?.setValue(imageType);
        this.promotionForm.get('image')?.setValue(imageBase64);

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



  removePromotion(id: number) {
    if (confirm('Do you confirm delete promotion?')){
      this.adminService.deletePromotion(id).subscribe({
        next:()=>{
          alert('promotion deleted!');
          this.loadPromotions();
        },
        error:err => console.log(err),
        complete:()=>console.log('promotion deleted !!!!')
      })
    }
  }


  protected readonly faPenToSquare = faPenToSquare;
  protected readonly faTrashCan = faTrashCan;
}
