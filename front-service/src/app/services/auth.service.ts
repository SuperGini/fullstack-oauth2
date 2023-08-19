import {inject, Injectable} from "@angular/core";
import {GatewayService} from "./gateway/gatewayService";
import tokenUrl from "../constants/tokenUrl";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {refreshTokenUrl} from "../constants/refreshTokenUrl";
import {catchError, throwError} from "rxjs";

//https://stackoverflow.com/questions/48075688/how-to-decode-the-jwt-encoded-token-payload-on-client-side-in-angular
//https://medium.com/@ryanchenkie_40935/angular-authentication-using-the-http-client-and-http-interceptors-2f9d1540eb8

@Injectable({providedIn: 'root'})
export class AuthService {

  public code: string = '';

  private gatewayService: GatewayService = inject(GatewayService);
  private router: Router = inject(Router);

  getAuthToken() {
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

  regenerateAuthToken() {
    return this.gatewayService.regenerateAuthToken(refreshTokenUrl(), null)
      .pipe(
        catchError(this.handleError.bind(this))
      )
      .subscribe(token => {
        sessionStorage.setItem('id_token', token.id_token);
        sessionStorage.setItem('refresh_token', token.refresh_token);
        sessionStorage.setItem('access_token', token.access_token);

        console.log(`id_token = ${token.id_token}`);
        console.log(`refresh_token = ${token.refresh_token}`);
        console.log(`access_token = ${token.access_token}`);
      })
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 400 && error.url?.includes('refresh_token')) {
      this.router.navigate(['/login']);
    }
    return throwError(() => new Error(`Something bad happened; please try again later. + ${error.error} and status ${error.status}`));
  }

  regenerateAuthToken2() {
    return this.gatewayService.regenerateAuthToken(refreshTokenUrl(), null)
      .pipe(
        catchError(this.handleError.bind(this))
      )

  }

}
