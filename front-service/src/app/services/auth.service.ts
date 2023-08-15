import {inject, Injectable} from "@angular/core";
import {GatewayService} from "./gateway/gatewayService";
import tokenUrl from "../constants/tokenUrl";
import {HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({providedIn: 'root'})
export class AuthService {

  public code: string = '';

  private gatewayService: GatewayService = inject(GatewayService);
  private router :Router = inject(Router);

  getAuthToken() {
    const client = 'client';
    const secret = 'secret';
    const basicAuth = `Basic ` + btoa(`${client}:${secret}`);

    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'Authorization': basicAuth
    });

    const options = {headers: headers};

    return this.gatewayService.getAuthToken(tokenUrl(this.code), null)
      .subscribe(token => {
        sessionStorage.setItem('id_token', token.id_token);
        sessionStorage.setItem('refresh_token', token.refresh_token);
        sessionStorage.setItem('access_token', token.access_token);

        this.router.navigate(['/home/table']);

        console.log(`id_token = ${token.id_token}`);
        console.log(`refresh_token = ${token.refresh_token}`);
        console.log(`access_token = ${token.access_token}`);
      })
  }

}
