import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {inject, Injectable} from "@angular/core";
import {State} from "../state/state";

@Injectable()
export class LogoutInterceptor implements HttpInterceptor{

  private state = inject(State);

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.state.timeoutTime.next(Date.now());
    return next.handle(req);
  }
}
