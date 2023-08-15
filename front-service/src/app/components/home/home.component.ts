import {Component} from "@angular/core";
import {LeftComponent} from "./left/left.component";
import {TableComponent} from "./right/table/table.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: "home-component",
  templateUrl: "./home.html",
  styleUrls: ["./home.css"],
  imports: [
    LeftComponent,
    TableComponent,
    RouterOutlet
  ],
  standalone: true
})
export class HomeComponent{

}
