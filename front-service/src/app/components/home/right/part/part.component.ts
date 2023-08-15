import {Component} from "@angular/core";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {PartRequest} from "../../../../utility/request/partRequest";
import {CarRequest} from "../../../../utility/request/carRequest";
import {PartService} from "../../../../services/part.service";
import {isPositiveNumberValidator, isValidStringValidator} from "../../../../validators/validators";
import {NgIf} from "@angular/common";


@Component({
  selector: 'part-component',
  templateUrl: './part.html',
  styleUrls: ['./part.css'],
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  standalone: true
})
export class PartComponent {

  public createPartForm: FormGroup;

  //it needs to de initialized in constructor, or it doesn't work
  constructor(private partService: PartService) {
    this.createPartForm = new FormGroup({
      partName: new FormControl(null, isValidStringValidator()),
      price: new FormControl(null, isPositiveNumberValidator()),
      quantity: new FormControl(null, isPositiveNumberValidator()),
      manufacturer: new FormControl(null, isValidStringValidator()),
      carModel: new FormControl(null, isValidStringValidator())
    });
  }


  addPart() {
    const partRequest = this.createPartRequest();


    this.partService.createPart(partRequest);
    this.createPartForm.reset();

  }

  private createPartRequest() {
    const form = this.createPartForm.value;

    const carRequest: CarRequest = {
      manufacturer: form.manufacturer,
      carModel: form.carModel
    }

    const partRequest: PartRequest = {
      partName: form.partName,
      quantity: form.quantity,
      price: form.price,
      carRequest: carRequest
    }
    return partRequest;
  }

  get price() {
    return this.createPartForm.get('price');
  }

  get partName() {
    return this.createPartForm.get('partName');
  }

  get quantity() {
    return this.createPartForm.get('quantity');
  }

  get manufacturer() {
    return this.createPartForm.get('manufacturer');
  }

  get carModel() {
    return this.createPartForm.get('carModel');
  }

}
