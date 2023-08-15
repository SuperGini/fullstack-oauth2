import {Component, inject} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {PartService} from "../../../services/part.service";
import {ActivatedRoute, Router} from "@angular/router";

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

  private partService = inject(PartService);
  private router = inject(Router);
  private activatedRoute = inject(ActivatedRoute);

  redirectHome() {
        this.router.navigate(['./home', 'table']);
  }


  addPart() {
      this.router.navigate(['./home', 'part']);
  }


}
