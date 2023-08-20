import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {inject, Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private authService = inject(AuthService);

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = sessionStorage?.getItem('id_token');
    const bearerToken: string = `Bearer ${token}`;
    const path: boolean = req.url.includes('oauth2/token');
    const path2: boolean = req.url.includes('connect/logout');


    if (!path && !path2) {
      const authRequest = req.clone({
        headers: req.headers
          .append('Authorization', bearerToken)
      });

      return next
        .handle(authRequest)
        .pipe(
          catchError(this.handleError.bind(this))
        );
    }
    console.log("token interceptor calling core-service")
    return next.handle(req);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 401) {
      console.error(`An error occurred: ${error.error} with status: ${error.status}`);
      this.authService.regenerateAuthToken();

    }

    return throwError(() =>
      new Error(`Something bad happened; please try again later. ${error.error} and status ${error.status}`));
  }

}
