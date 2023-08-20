import {Component, inject, OnInit} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";

import {Router} from "@angular/router";

import {NgIf} from "@angular/common";

import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthService} from "../../../services/auth.service";
import {LogoutService} from "../../../services/logout.service";

@Component({
  selector: 'left-component',
  templateUrl: './left.html',
  styleUrls: ['./left.css'],
  imports: [
    MatIconModule,
    NgIf
  ],
  standalone: true
})
export class LeftComponent implements OnInit{

  role: string | undefined;
  private router: Router = inject(Router);
  private authService: AuthService = inject(AuthService);
  private logoutService = inject(LogoutService);

  ngOnInit(): void {
    this.setRoleFiled();
  }

  redirectHome() {
        this.router.navigate(['./home', 'table']);
  }


  addPart() {
      this.router.navigate(['./home', 'part']);
  }

  private setRoleFiled() {
    const token = <string>sessionStorage.getItem("id_token");

    const jwtHelper: JwtHelperService = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);
    const roles: string[] = decodedToken.authorities;

    this.role = roles.filter(role => 'ADMIN' === role)
        .at(0);
  }

  logout() {
    this.logoutService.logout();
  }
}
