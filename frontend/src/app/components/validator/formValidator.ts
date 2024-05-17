import {FormControl, ValidationErrors} from "@angular/forms";

export class FormValidator{
  static noWhite(control:FormControl):ValidationErrors | null{
    if ((control.value != null) && (control.value.trim().length == 0)){
      return {noWhite: true};
    }else return null;

  }
}
