import {Component} from "@angular/core";
import {LeftComponent} from "./left/left.component";
import {RightComponent} from "./right/right.component";

@Component({
  selector: "home-component",
  templateUrl: "./home.html",
  styleUrls: ["./home.css"],
  imports: [
    LeftComponent,
    RightComponent
  ],
  standalone: true
})
export class HomeComponent{

}
