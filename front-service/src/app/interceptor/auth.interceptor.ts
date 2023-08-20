import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const basicAuth = this.generateBasicAuthForClient();
    const path = req.url.includes('oauth2/token');
    const path2: boolean = req.url.includes('connect/logout');

    if (path) {
      const reqBasicHeader = req.clone({
        headers: req.headers
                    .append('Authorization', basicAuth)
      });

      return next.handle(reqBasicHeader);
    }

    if (path2) {
      const reqBasicHeader = req.clone({
        headers: req.headers
          .append('Authorization', basicAuth)
      });
      console.log("path2 ++++++ interceptor")
      return next.handle(reqBasicHeader);
    }

    return next.handle(req);
  }

  private generateBasicAuthForClient() {
    const client = 'client';
    const secret = 'secret';
    return `Basic ` + btoa(`${client}:${secret}`);

  }
}
