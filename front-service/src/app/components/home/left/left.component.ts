import {Component} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'left-component',
  templateUrl: './left.html',
  styleUrls: ['./left.css'],
  imports: [
    MatIconModule
  ],
  standalone: true
})
export class LeftComponent {

}
