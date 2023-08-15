import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = sessionStorage.getItem('id_token');
    const bearerToken = `Bearer ${token}`;

    const authRequest = req.clone({
      headers: req.headers
                      .append('Authorization', bearerToken)
    });

    return next.handle(authRequest);
  }

}
