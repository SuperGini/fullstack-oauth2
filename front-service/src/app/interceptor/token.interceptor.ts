import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = sessionStorage.getItem('id_token');
    const bearerToken = `Bearer ${token}`;

    const path = req.url.includes('oauth2/token');

    if (!path) {
      const authRequest = req.clone({
        headers: req.headers
          .append('Authorization', bearerToken)
      });
      return next.handle(authRequest);
    }

    return next.handle(req);


  }

}
