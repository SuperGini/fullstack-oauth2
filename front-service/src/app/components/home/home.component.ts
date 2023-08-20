import {Component, inject, OnInit} from "@angular/core";
import {LeftComponent} from "./left/left.component";
import {TableComponent} from "./right/table/table.component";
import {RouterOutlet} from "@angular/router";
import {LogoutService} from "../../services/logout.service";

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
export class HomeComponent implements OnInit{

  private logout = inject(LogoutService);

  ngOnInit(): void {
    this.logout.logoutTimer();
  }



}
