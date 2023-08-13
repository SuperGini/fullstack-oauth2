import {inject, Injectable} from "@angular/core";
import {GatewayService} from "./gatewayService";
import tokenUrl from "../constants/tokenUrl";
import {HttpHeaders} from "@angular/common/http";
import {map, tap} from "rxjs";
import {Token} from "../utility/token";

@Injectable({providedIn:'root'})
export class AuthService {

  public code: string = '';

  private gatewayService = inject(GatewayService);

  getAuthToken(){
    const client = 'client';
    const secret = 'secret';
    const basicAuth = `Basic ` + btoa(`${client}:${secret}`);

    const headers = new HttpHeaders( {
      'content-type': 'application/json',
      'Authorization': basicAuth
    });

    const options = {headers: headers};

    return this.gatewayService.getAuthToken(tokenUrl(this.code), null, options)
      .subscribe(token => {
        sessionStorage.setItem('id_token', token.id_token);
            sessionStorage.setItem('refresh_token', token.refresh_token);
            sessionStorage.setItem('access_token', token.access_token);

            console.log(
              `id_token = ${token.id_token}` +
              `refresh_token = ${token.refresh_token}` +
              `access_token = ${token.access_token}`
            );
      })
  }

  private convert (x: Token){
    return x;
  }


}
