import {Component} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import redirect from "../../constants/redirect";

@Component({
  selector: "login-component",
  templateUrl: "./login.html",
  styleUrls: ["./login.css"],
  imports: [
    MatIconModule
  ],
  standalone: true
})
export class LoginComponent {

  redirect() {
    window.location.href = redirect();
  }
}
