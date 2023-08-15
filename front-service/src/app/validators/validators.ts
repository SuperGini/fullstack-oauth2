import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

const positiveNumberRegex = new RegExp(/^[1-9]\d*$/);
const stringRegex = new RegExp(/^[a-zA-Z\s]+$/);

export function isPositiveNumberValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const isPositiveNumber = positiveNumberRegex.test(control.value);

    if(!isPositiveNumber){
      return {invalidNumber: true}
    }
    // returning null means that the number is valid (positive number);
    return null;
  }
}


export function isValidStringValidator (): ValidatorFn  {
  return (control: AbstractControl): ValidationErrors | null => {
    const isOnlyCharacters = stringRegex.test(control.value);

    if(!isOnlyCharacters){
      return {invalidString: true};
    }
    // returning null means that the string is valid;
    return null;
  }
}
