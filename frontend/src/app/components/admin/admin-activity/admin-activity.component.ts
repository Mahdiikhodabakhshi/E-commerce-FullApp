import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../../services/admin.service";
import {ActivityContent} from "../../../interfaces/ActivityPageable";
import {faPenToSquare} from "@fortawesome/free-solid-svg-icons/faPenToSquare";
import {faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {FormValidator} from "../../validator/formValidator";

@Component({
  selector: 'app-admin-activity',
  templateUrl: './admin-activity.component.html',
  styleUrls: ['./admin-activity.component.css']
})
export class AdminActivityComponent {


  activityList !:ActivityContent[];

  page = 0;
  size = 5;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;

  edit: boolean = false;



  titleFilter?: string;
  locationFilter?: string;

  activityForm: FormGroup = this.formBuilder.group({
    id:[''],
    title: ['', [Validators.required,FormValidator.noWhite]],
    description: ['', [Validators.required , FormValidator.noWhite]],
    location: ['', [Validators.required , FormValidator.noWhite]],
    uploadDate: [new Date()],
    image:[''],
    imageType:['']
  });

  get id():any{
    return this.activityForm.get('id')?.value;
  }

  get title():any{
    return this.activityForm.get('title');
  }
  get description():any{
    return this.activityForm.get('description');
  }
  get location():any{
    return this.activityForm.get('location');
  }




  get imageType():any{
    return this.activityForm.get('imageType')?.value
  }

  get image():any{
    return this.activityForm.get('image')?.value
  }



  constructor(private adminService:AdminService , private formBuilder: FormBuilder) {
    this.loadActivities();
  }


  private loadActivities() {

    const filter:string|undefined = this.buildFilters();

    this.adminService.getActivities(this.page , this.size , filter).subscribe({
      next:data => {
        console.log(data);
        this.activityList = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error:err => console.log(err),
      complete:()=> console.log('activityList loaded')
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
    this.loadActivities();

  }

  toPreviousPage() {
    this.page -=1;
    this.loadActivities();
  }

  toNextPage() {
    this.page += 1;
    this.loadActivities();

  }

  newItem() {
    this.edit = false;
    this.activityForm.reset();
    this.activityForm.get('uploadDate')?.setValue(new Date())

  }

  loadItem(activity: ActivityContent) {
    this.activityForm.setValue(activity);
    this.edit = true;

  }


  onSubmit() {
    if (this.edit){
      this.adminService.updateActivity(this.activityForm.getRawValue()).subscribe({
        next:value => {
          this.loadActivities()
        },
        error:err => console.log(err),
        complete:()=>console.log('activity updated')
      })
    }else {
      this.adminService.addActivity(this.activityForm.getRawValue()).subscribe({
        next:value => {
          this.loadActivities();
        },
        error:err => console.log(err),
        complete:()=>console.log('new activity created')
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
        this.activityForm.get('imageType')?.setValue(imageType);
        this.activityForm.get('image')?.setValue(imageBase64);

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



  removeActivity(id: number) {
    if (confirm('Do you confirm delete activity?')){
      this.adminService.deleteActivity(id).subscribe({
        next:()=>{
          alert('activity deleted!');
          this.loadActivities();
        },
        error:err => console.log(err),
        complete:()=>console.log('activity deleted !!!!')
      })
    }
  }

  protected readonly faPenToSquare = faPenToSquare;
  protected readonly faTrashCan = faTrashCan;
}
