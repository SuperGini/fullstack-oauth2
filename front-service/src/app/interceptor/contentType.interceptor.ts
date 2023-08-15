import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

export class ContentTypeInterceptor implements HttpInterceptor{

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const addHeadersToRequest = req.clone({
        headers: req.headers
                      .append('content-type', 'application/json')
    });

    return next.handle(addHeadersToRequest);
  }

}
